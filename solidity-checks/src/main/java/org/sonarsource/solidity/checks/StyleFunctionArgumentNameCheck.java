package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.FunctionDefinitionContext;
import org.sonarsource.solidity.frontend.SolidityParser.ParameterListContext;
import org.sonarsource.solidity.frontend.SolidityParser.ParameterContext;
import org.sonarsource.solidity.frontend.SolidityParser.IdentifierContext;

import java.util.List;

@Rule(key = StyleFunctionArgumentNameCheck.RULE_KEY)
public class StyleFunctionArgumentNameCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule204";

    @Override
    public ParseTree visitFunctionDefinition(FunctionDefinitionContext ctx) {

        String camelCasePattern = "([a-z]+[A-Z]+\\w+)+";

        ParameterListContext pctx = ctx.parameterList();
        List<ParameterContext> lp = pctx.parameter();

        for (ParameterContext pactx : lp) {
            IdentifierContext ictx = pactx.identifier();

            if (!ictx.getText().matches(camelCasePattern)) {
                ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Function Argument Name should use mixedCase.",
                        RULE_KEY);
            }
        }

        return super.visitFunctionDefinition(ctx);
    }
}
