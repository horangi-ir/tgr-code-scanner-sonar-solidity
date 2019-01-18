package org.sonarsource.solidity.checks;

import org.junit.Test;

public class StyleEventNameCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new StyleEventNameCheck(), "src/test/resources/StyleEventNameCheck/test_noncompliant.sol");
    }
}