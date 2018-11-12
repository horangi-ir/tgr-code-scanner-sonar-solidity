package org.sonarsource.solidity.checks;

import org.junit.Test;

public class ExplicitlyLabelVisibilityCheckTest {

    @Test
    public void test_noncompliant() {
        new CheckVerifier(new ExplicitlyLabelVisibilityCheck(),
                "src/test/resources/ExplicitlyLabelVisibilityCheck/test_noncompliant.sol");
    }

}