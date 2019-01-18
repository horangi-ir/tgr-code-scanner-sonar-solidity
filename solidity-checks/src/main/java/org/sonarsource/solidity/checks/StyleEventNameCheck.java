package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.EventDefinitionContext;
import org.sonarsource.solidity.frontend.SolidityParser.IdentifierContext;

@Rule(key = StyleEventNameCheck.RULE_KEY)
public class StyleEventNameCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule203";

    @Override
    public ParseTree visitEventDefinition(EventDefinitionContext etx) {

        IdentifierContext ictx = etx.identifier();

        String camelCasePattern = "([a-z]+[A-Z]+\\w+)+";

        if (!ictx.getText().matches(camelCasePattern)) {
            ruleContext().addIssue(etx.getStart(), etx.getStop(), "Event Name should use mixedCase.", RULE_KEY);
        }

        return super.visitEventDefinition(etx);
    }
}
