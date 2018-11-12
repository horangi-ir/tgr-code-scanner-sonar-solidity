package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.ElementaryTypeNameContext;

@Rule(key = UsingVarCheck.RULE_KEY)
public class UsingVarCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule139";

    @Override
    public ParseTree visitElementaryTypeName(ElementaryTypeNameContext ctx) {
        System.out.println("\n----");
        System.out.println(ctx.getText());

        if ("var".equals(ctx.getText())) {
            ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Using actual data type in place of var.", RULE_KEY);
        }
        return super.visitElementaryTypeName(ctx);
    }
}
