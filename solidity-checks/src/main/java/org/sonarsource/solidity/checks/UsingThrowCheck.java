package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.ThrowStatementContext;

@Rule(key = UsingThrowCheck.RULE_KEY)
public class UsingThrowCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule138";

    @Override
    public ParseTree visitThrowStatement(ThrowStatementContext ctx) {
        ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Use revert instead of throw.", RULE_KEY);
        return super.visitThrowStatement(ctx);
    }
}
