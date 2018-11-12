package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.FunctionDefinitionContext;
import org.sonarsource.solidity.frontend.SolidityParser.ModifierListContext;

@Rule(key = ImplicitVisibilityLevelCheck.RULE_KEY)
public class ImplicitVisibilityLevelCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule115";

    @Override
    public ParseTree visitFunctionDefinition(FunctionDefinitionContext ctx) {

        ModifierListContext mctx = ctx.modifierList();

        if (mctx != null) {
            if (mctx.ExternalKeyword().size() == 0 && mctx.PublicKeyword().size() == 0
                    && mctx.PrivateKeyword().size() == 0 && mctx.InternalKeyword().size() == 0) {
                ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Explicitly declare function visibility level.",
                        RULE_KEY);
            }
        }

        return super.visitFunctionDefinition(ctx);
    }
}
