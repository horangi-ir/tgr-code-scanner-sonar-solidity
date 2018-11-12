package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.ExpressionContext;

@Rule(key = BalanceEqualityCheck.RULE_KEY)
public class BalanceEqualityCheck extends IssuableVisitor {

  public static final String RULE_KEY = "ExternalRule104";

  @Override
  public ParseTree visitExpression(ExpressionContext ctx) {

    ParseTree expr = ctx.getChild(1);
    if (expr != null && expr.getText().startsWith("==")) {

      if (ctx.getChild(0).getText().startsWith("this.balance")) {
        ruleContext().addIssue(ctx.getStart(), ctx.getStop(), "Avoid checking for strict balance equality.", RULE_KEY);
      }
    }
    return super.visitExpression(ctx);
  }
}
