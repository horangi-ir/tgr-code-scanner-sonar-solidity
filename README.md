# The list of original 15 rules
**Note:** There is no rule 5, 10, 11, 12, 18, 19, 20, 21, 22

| SN | Name | Description |
|-------------- |--------------|-----|
| ExternalRule1 | LatestVersionCheck ||
| ExternalRule2 | ContractNotEmptyCheck ||
| ExternalRule3 | EmptyFileCheck ||
| ExternalRule4 | EmptyFunctionCheck ||
| ExternalRule6 | CurlyBraceCheck ||
| ExternalRule7 | ConstructorVisibilityCheck ||
| ExternalRule8 | DeprecatedConstructorCheck ||
| ExternalRule9 | CognitiveComplexityCheck, CognitiveComplexityVisitor ||
| ExternalRule13 | DeprecatedSuicideCheck ||
| ExternalRule14 | AvoidTxOriginCheck ||
| ExternalRule15 | AvoidSha3Check ||
| ExternalRule16 | AccessRestrictionPatternCheck ||
| ExternalRule17 | GuardCheckPatternCheck ||
| ExternalRule22 | CheckEffectsCheck ||
| ExternalRule23 | BytesLowerGasCheck ||

---
# The list of new 40 rules

Note: some new rules implemented in original rules.
***TBI*** = To Be Implemented.

| SN | Name | Description |
|-----------------|-------------|-----|
| ExternalRule102 | AddressHardcodedCheck ||
| ExternalRule103 | ***TBI*** ||
| ExternalRule104 | BalanceEqualityCheck ||
| ExternalRule105 | BytesLowerGasCheck | = ExternalRule23|
| ExternalRule106 | ***TBI*** ||
| ExternalRule107 | CompilerVersionNotFixedCheck ||
| ExternalRule108 | ***TBI*** ||
| ExternalRule109 | ***TBI*** ||
| ExternalRule111 | ***TBI*** ||
| ExternalRule112 | ***TBI*** ||
| ExternalRule113 | LoopCheck ||
| ExternalRule114 | ***TBI*** ||
| ExternalRule115 | ImplicitVisibilityLevelCheck ||
| ExternalRule116 | IntegerOverflowUnderflowCheck ||
| ExternalRule117 | ***TBI*** ||
| ExternalRule118 | ***TBI*** ||
| ExternalRule119 | ***TBI*** ||
| ExternalRule120 | ***TBI*** ||
| ExternalRule121 | ***TBI*** ||
| ExternalRule122 | ***TBI*** ||
| ExternalRule123 | ***TBI*** ||
| ExternalRule124 | ***TBI*** ||
| ExternalRule125 | StyleGuideViolationCheck | = ExternalRule203+204+205|
| ExternalRule126 | ***TBI*** ||
| ExternalRule127 | ***TBI*** ||
| ExternalRule128 | ***TBI*** ||
| ExternalRule129 | ***TBI*** ||
| ExternalRule130 | ***TBI*** ||
| ExternalRule131 | UsingVarCheck | = ExternalRule139|
| ExternalRule132 | AvoidTxOriginCheck | = ExternalRule14|
| ExternalRule133 | ***TBI*** ||
| ExternalRule134 | ***TBI*** ||
| ExternalRule135 | ***TBI*** ||
| ExternalRule136 | AvoidSha3Check | = ExternalRule15|
| ExternalRule137 | DeprecatedSuicideCheck | = ExternalRule13|
| ExternalRule138 | UsingThrowCheck ||
| ExternalRule139 | UsingVarCheck ||
| ExternalRule140 | ExplicitlyLabelVisibilityCheck ||
| ExternalRule203 | StyleEventNameCheck ||
| ExternalRule204 | StyleFunctionArgumentNameCheck ||
| ExternalRule205 | StyleFunctionNameCheck ||


---


# Sonar-Solidity [![Build Status](https://travis-ci.org/sagap/sonar-solidity.svg?branch=master)](https://travis-ci.org/sagap/sonar-solidity) [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.solidity%3Asonar-solidity&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.sonarsource.solidity%3Asonar-solidity) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.solidity%3Asonar-solidity&metric=coverage)](https://sonarcloud.io/component_measures?id=org.sonarsource.solidity%3Asonar-solidity&metric=coverage)

**SonarSolidity**: is a SonarQube static code analyzer for Solidity Smart Contracts.

To begin with you should install a SonarQube 7.2+ instance (https://www.sonarqube.org/downloads/), please follow the instructions provided.
As soon as you installed SonarQube, then download the latest release from [here](https://github.com/sagap/sonar-solidity/releases) and copy paste it in the folder  **sonarqube/extensions/plugins/**  then start your instance and you are ready to go!

## Building

```bash
git clone --recursive https://github.com/sagap/sonar-solidity.git
mvn clean install
```

## Features
* Metrics (cognitive complexity, number of lines, number of contracts etc)
* 25 Rules

[ANTLR4](https://github.com/solidityj/solidity-antlr4) grammar to build the Parser and the Lexer.

SonarSolidity supports the import of reports from[ ```Solium linter version 1.0.0``` ](http://solium.readthedocs.io/en/latest/).
* 13 Security Rules
* 32 Style Rules

## Documentation

Please read [documentation](https://github.com/sagap/sonar-solidity/blob/master/Sonar%20Solidity%20Docs.pdf) on how to take advantage of this feature.

## License

Licensed under the [GNU Lesser General Public License, Version 3.0](http://www.gnu.org/licenses/lgpl.txt)
