package org.sonarsource.solidity.checks;

import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.check.Rule;
import org.sonarsource.solidity.frontend.SolidityParser.ElementaryTypeNameContext;
import org.sonarsource.solidity.frontend.SolidityParser.StateVariableDeclarationContext;
import org.sonarsource.solidity.frontend.SolidityParser.UsingForDeclarationContext;
import org.sonarsource.solidity.frontend.SolidityParser.TypeNameContext;
import org.sonarsource.solidity.frontend.SolidityParser.ContractPartContext;
import org.sonarsource.solidity.frontend.SolidityParser.ContractDefinitionContext;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

@Rule(key = IntegerOverflowUnderflowCheck.RULE_KEY)
public class IntegerOverflowUnderflowCheck extends IssuableVisitor {

    public static final String RULE_KEY = "ExternalRule116";

    @Override
    public ParseTree visitContractDefinition(ContractDefinitionContext ctx) {

        HashSet<String> usingSet = new HashSet<String>();
        HashMap<String, StateVariableDeclarationContext> stateMap = new HashMap<String, StateVariableDeclarationContext>();

        List<ContractPartContext> lcpc = ctx.contractPart();
        int s = lcpc.size();

        for (int i = 0; i < s; i++) {
            ContractPartContext c = lcpc.get(i);

            StateVariableDeclarationContext sctx = c.stateVariableDeclaration();
            if (sctx != null) {
                TypeNameContext tctx = sctx.typeName();
                ElementaryTypeNameContext ectx = tctx.elementaryTypeName();

                if (ectx != null) {
                    if ("uint256".equals(ectx.getText()) || "uint".equals(ectx.getText())
                            || "int".equals(ectx.getText()) || "uint32".equals(ectx.getText())
                            || "uint16".equals(ectx.getText()) || "uint8".equals(ectx.getText())) {
                        stateMap.put(ectx.getText(), sctx);
                    }
                }
            }

            UsingForDeclarationContext uctx = c.usingForDeclaration();
            if (uctx != null) {
                TypeNameContext tctx = uctx.typeName();
                ElementaryTypeNameContext ectx = tctx.elementaryTypeName();

                if (ectx != null) {
                    if ("uint256".equals(ectx.getText()) && "SafeMath".equals(uctx.identifier().getText())
                            || "uint".equals(ectx.getText()) && "SafeMath".equals(uctx.identifier().getText())
                            || "int".equals(ectx.getText()) && "SafeMath".equals(uctx.identifier().getText())
                            || "uint8".equals(ectx.getText()) && "SafeMath8".equals(uctx.identifier().getText())
                            || "uint16".equals(ectx.getText()) && "SafeMath16".equals(uctx.identifier().getText())
                            || "uint32".equals(ectx.getText()) && "SafeMath32".equals(uctx.identifier().getText())) {
                        usingSet.add(ectx.getText());
                    }
                }
            }
        }

        for (HashMap.Entry<String, StateVariableDeclarationContext> entry : stateMap.entrySet()) {
            if (!usingSet.contains(entry.getKey())) {
                ruleContext().addIssue(entry.getValue().getStart(), entry.getValue().getStop(),
                        "Use the SafeMath(*) library that checks for overflows.", RULE_KEY);
            }
        }

        return super.visitContractDefinition(ctx);
    }
}
