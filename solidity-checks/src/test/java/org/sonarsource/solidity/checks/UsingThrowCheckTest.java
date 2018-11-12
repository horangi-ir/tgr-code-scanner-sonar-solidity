package org.sonarsource.solidity.checks;

import org.junit.Test;

public class UsingThrowCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new UsingThrowCheck(), "src/test/resources/UsingThrowCheck/test_noncompliant.sol");
    }
}