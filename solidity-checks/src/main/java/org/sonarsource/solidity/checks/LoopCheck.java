package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.ForStatementContext;

@Rule(key = LoopCheck.RULE_KEY)
public class LoopCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule113";

    @Override
    public ParseTree visitForStatement(ForStatementContext ctx) {

        ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "The unbounded for loop is an anti-pattern.", RULE_KEY);

        return super.visitForStatement(ctx);
    }
}
