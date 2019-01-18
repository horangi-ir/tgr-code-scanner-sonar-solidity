package org.sonarsource.solidity.checks;

import org.junit.Test;

public class StyleFunctionNameCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new StyleFunctionNameCheck(),
                "src/test/resources/StyleFunctionNameCheck/test_noncompliant.sol");
    }
}