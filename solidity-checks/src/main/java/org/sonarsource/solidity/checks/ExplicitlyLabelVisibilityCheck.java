package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.StateVariableDeclarationContext;
import org.sonarsource.solidity.frontend.SolidityParser.FunctionDefinitionContext;

@Rule(key = ExplicitlyLabelVisibilityCheck.RULE_KEY)
public class ExplicitlyLabelVisibilityCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule140";

    @Override
    public ParseTree visitStateVariableDeclaration(StateVariableDeclarationContext ctx) {

        if (ctx.PublicKeyword().size() == 0 && ctx.InternalKeyword().size() == 0 && ctx.PrivateKeyword().size() == 0
                && ctx.ConstantKeyword().size() == 0) {
            ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Explicitly label the visibility.", RULE_KEY);
        }

        return super.visitStateVariableDeclaration(ctx);
    }

    @Override
    public ParseTree visitFunctionDefinition(FunctionDefinitionContext ctx) {

        if (ctx.modifierList() != null) {
            if (ctx.modifierList().ExternalKeyword().size() == 0 && ctx.modifierList().PublicKeyword().size() == 0
                    && ctx.modifierList().InternalKeyword().size() == 0
                    && ctx.modifierList().PrivateKeyword().size() == 0) {
                ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Explicitly label the visibility.", RULE_KEY);
            }
        }
        return super.visitFunctionDefinition(ctx);
    }

}
