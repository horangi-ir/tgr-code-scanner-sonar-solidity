package org.sonarsource.solidity.checks;

import org.junit.Test;

public class BalanceEqualityCheckTest {

  @Test
  public void test_noncompliant() {
    new CheckVerifier(new BalanceEqualityCheck(), "src/test/resources/BalanceEqualityCheck/test_noncompliant.sol");
  }

}