package org.sonarsource.solidity.checks;

import org.junit.Test;

public class UsingVarCheckTest {

  @Test
  public void test_noncompliant() {
    new CheckVerifier(new UsingVarCheck(), "src/test/resources/UsingVarCheck/test_noncompliant.sol");
  }

}