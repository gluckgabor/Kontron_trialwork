# Cucumber BDD Framework with Extent Reports for Rest-API Automation

## Overview
  This framework is developed using Behavior-Driven Development (BDD) principles with Cucumber and integrated with Extent Reports. It is designed specifically for automating REST APIs, providing robust reporting and flexibility for handling API responses and requests.
### Key Features
#### Cucumber BDD:
- Enables writing human-readable test scenarios that bridge the gap between non-technical stakeholders and developers.
#### Extent Reports:
- Generates visually appealing and detailed test reports with screenshots, logs, and results.

## Prerequisites
- Java 17 or higher
- Maven 3.6v or higher
- Junit 5.0v or higher
- RestAssured 5.4.0v or higher
- Cucumber 7.16v or higher
- Extent-reports 5.0v or higher

## Jenkins CI/CD
#### General Section:
 Select "This project is parameterized" and set below parameters.
1. For 1st parameter, select String parameter and set below details:
 - Name: Features
 - Default value: src/test/resources/features
 - Description: -- Pass the features path here by separated coma (,). -- By default, it will pick up all features.
2. For 2nd parameter, select choice parameter and set below details:
 - Name: Tags
 - Choices: Regression, Smoke, Sanity
 - Description: -- Choose the tags. By default Regression
#### Build Section:
1. For Root POM, give as "pom.xml"
2. For Goals and Options, use the following command:
```bash
clean verify -Dcucumber.options="--features ${Features} --tags @${Tags}"
```
3. Go to "Advanced" and check the "use custom workspace". Add project directory path.

Note: Add project path in the custom workspace field and Also remainings as per requirements.
