package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.FunctionDefinitionContext;
import org.sonarsource.solidity.frontend.SolidityParser.IdentifierContext;

@Rule(key = StyleFunctionNameCheck.RULE_KEY)
public class StyleFunctionNameCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule205";

    @Override
    public ParseTree visitFunctionDefinition(FunctionDefinitionContext ctx) {

        IdentifierContext ictx = ctx.identifier();

        String camelCasePattern = "([a-z]+[A-Z]+\\w+)+";

        if (!ictx.getText().matches(camelCasePattern)) {
            ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Function Name should use mixedCase.", RULE_KEY);
        }

        return super.visitFunctionDefinition(ctx);
    }
}
