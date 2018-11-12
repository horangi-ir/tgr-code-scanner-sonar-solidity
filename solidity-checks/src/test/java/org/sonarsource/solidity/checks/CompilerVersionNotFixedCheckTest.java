package org.sonarsource.solidity.checks;

import org.junit.Test;

public class CompilerVersionNotFixedCheckTest {

  @Test
  public void test_noncompliant() {
    new CheckVerifier(new CompilerVersionNotFixedCheck(),
        "src/test/resources/CompilerVersionNotFixedCheck/test_noncompliant.sol");
  }

  @Test
  public void test_compliant() {
    CheckVerifier.verifyNoIssue(new CompilerVersionNotFixedCheck(),
        "src/test/resources/CompilerVersionNotFixedCheck/test_compliant.sol");
  }

}