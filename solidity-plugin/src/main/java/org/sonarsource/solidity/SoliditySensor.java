package org.sonarsource.solidity;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.sonar.api.SonarProduct;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.InputFile.Type;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.highlighting.NewHighlighting;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.FileLinesContextFactory;
import org.sonar.api.utils.Version;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.solidity.checks.CheckList;
import org.sonarsource.solidity.checks.CognitiveComplexityVisitor;
import org.sonarsource.solidity.checks.IssuableVisitor;
import org.sonarsource.solidity.checks.RuleContext;
import org.sonarsource.solidity.frontend.SolidityParser.SourceUnitContext;
import org.sonarsource.solidity.frontend.SolidityParsingPhase;

public class SoliditySensor implements Sensor {

  private static final Logger LOG = Loggers.get(SoliditySensor.class);

  public static final Version SQ_VERSION = Version.create(6, 7);

  protected static final String REPORT_PATH_KEY = "sonar.solidity.reportPath";
  private Collection<IssuableVisitor> checks;
  protected CognitiveComplexityVisitor cognitiveComplexity;

  public static final ImmutableList<String> KEYWORDS = ImmutableList.<String>builder()
    .add(SolidityKeywords.get()).build();

  public static final ImmutableList<String> KEYWORD_TYPES = ImmutableList.<String>builder()
    .add(SolidityKeywords.getKeyowrdTypes()).build();

  public SoliditySensor(CheckFactory checkFactory, FileLinesContextFactory fileLinesContextFactory) {
    this.checks = checkFactory.<IssuableVisitor>create(SolidityRulesDefinition.REPO_KEY)
      .addAnnotatedChecks((Iterable) CheckList.returnChecks())
      .all();
  }

  @Override
  public void describe(SensorDescriptor descriptor) {
    descriptor
      .onlyOnLanguage(Solidity.KEY)
      .name("SonarSolidity")
      .onlyOnFileType(Type.MAIN);
  }

  protected String reportPathKey() {
    return REPORT_PATH_KEY;
  }

  @Override
  public void execute(SensorContext context) {
    FileSystem fileSystem = context.fileSystem();

    FilePredicate mainFilePredicate = fileSystem.predicates().and(
      fileSystem.predicates().hasType(InputFile.Type.MAIN),
      fileSystem.predicates().hasLanguage(Solidity.KEY));
    List<InputFile> inputFiles = new ArrayList<>();
    fileSystem.inputFiles(mainFilePredicate).forEach(inputFiles::add);
    analyzeFiles(context, inputFiles);
  }

  public void analyzeFiles(SensorContext context, List<InputFile> files) {
    for (InputFile file : files) {
      String lastAnalyzedFile = file.toString();
      if (inSonarQube(context)) {
        try {
          LOG.debug("Analyzing: " + lastAnalyzedFile);
          SolidityParsingPhase parsing = new SolidityParsingPhase();
          SourceUnitContext suc = parsing.parse(file.contents());
          getSyntaxHighlighting(parsing, context, file).save();
          saveFileMeasures(context, computeMeasures(parsing, suc, file), file);
          SolidityCpd.addTokensCpd(context.newCpdTokens().onFile(file), file.contents());
          RuleContext ruleContext = new SolidityRuleContext(file, context);
          saveIssues(file, ruleContext);
        } catch (IOException e) {
          LOG.debug(e.getMessage(), e);
        }
      } else {
        LOG.debug(lastAnalyzedFile);
      }
    }
  }

  private void saveIssues(InputFile file, RuleContext ruleContext) throws IOException {
    SourceUnitContext suc = new SolidityParsingPhase().parse(file.contents());
    for (IssuableVisitor check : checks) {
      check.setRuleContext(ruleContext);
      check.visit(suc);
    }
  }

  private FileMeasures computeMeasures(SolidityParsingPhase parser, SourceUnitContext suc, InputFile file) throws IOException {
    MetricsVisitor metricsVisitor = new MetricsVisitor(parser, suc);
    cognitiveComplexity = new CognitiveComplexityVisitor(new SolidityParsingPhase().parse(file.contents()));
    int totalComplexity = cognitiveComplexity.sumAllFunctionsComplexity();
    metricsVisitor.fileMeasures.setFileCognitiveComplexity(totalComplexity);
    return metricsVisitor.fileMeasures;
  }

  public NewHighlighting getSyntaxHighlighting(SolidityParsingPhase parser, SensorContext context, InputFile inputFile) {
    NewHighlighting highlighting = context.newHighlighting().onFile(inputFile);
    SyntaxHighlightingVisitor visitor = new SyntaxHighlightingVisitor(highlighting);
    visitor.visitTokens(parser.getTokens());
    visitor.highlightComments(parser.comments);
    return highlighting;
  }

  private static void saveFileMeasures(SensorContext context, FileMeasures fileMeasures, InputFile inputFile) {
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getLinesOfCodeNumber()).forMetric(CoreMetrics.NCLOC).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getCommentLinesNumber()).forMetric(CoreMetrics.COMMENT_LINES).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getContractNumber()).forMetric(CoreMetrics.CLASSES).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getFunctionNumber()).forMetric(CoreMetrics.FUNCTIONS).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getStatementNumber()).forMetric(CoreMetrics.STATEMENTS).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getFileCognitiveComplexity()).forMetric(CoreMetrics.COGNITIVE_COMPLEXITY).save();
    context.<Integer>newMeasure().on(inputFile).withValue(fileMeasures.getFileComplexity()).forMetric(CoreMetrics.COMPLEXITY).save();
  }

  private static boolean inSonarQube(SensorContext context) {
    return !context.getSonarQubeVersion().isGreaterThanOrEqual(SQ_VERSION)
      || context.runtime().getProduct() != SonarProduct.SONARLINT;
  }
}
