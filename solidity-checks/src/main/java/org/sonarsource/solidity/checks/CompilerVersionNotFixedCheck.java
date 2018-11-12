package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.PragmaDirectiveContext;

@Rule(key = CompilerVersionNotFixedCheck.RULE_KEY)
public class CompilerVersionNotFixedCheck extends IssuableVisitor {

  public static final String RULE_KEY = "ExternalRule107";

  @Override
  public ParseTree visitPragmaDirective(PragmaDirectiveContext ctx) {

    if (isNotFixedVersion(ctx.pragmaValue().getText())) {
      ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Solidity compiler version not fixed", RULE_KEY);
    }
    return super.visitPragmaDirective(ctx);
  }

  private static boolean isNotFixedVersion(String version) {
    if (version.startsWith("^")) {
      return true;
    } else {
      return false;
    }
  }

}
