package org.sonarsource.solidity.checks;

import org.junit.Test;

public class LoopCheckTest {

    @Test
    public void test() {
        new CheckVerifier(new LoopCheck(), "src/test/resources/LoopCheck/test_noncompliant.sol");
    }
}