package org.sonarsource.solidity.checks;

import org.junit.Test;

public class IntegerOverflowUnderflowCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new IntegerOverflowUnderflowCheck(),
                "src/test/resources/IntegerOverflowUnderflowCheck/test_noncompliant.sol");
    }
}