package org.sonarsource.solidity.checks;

import org.junit.Test;

public class StyleFunctionArgumentNameCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new StyleFunctionArgumentNameCheck(),
                "src/test/resources/StyleFunctionArgumentNameCheck/test_noncompliant.sol");
    }
}