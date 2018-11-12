package org.sonarsource.solidity.checks;

import org.junit.Test;

public class ImplicitVisibilityLevelCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new ImplicitVisibilityLevelCheck(),
                "src/test/resources/ImplicitVisibilityLevelCheck/test_noncompliant.sol");
    }
}