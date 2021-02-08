# appuitest
This is an **ongoing** project for Application UI automation, using: 

- TestNG: for tests execution
- Appium: for tests creation
- Junit5: for group assertion
- Maven: for dependencies management
- Slf4j & log4j: for logging
- Allure: for report
- Page Object Model
- Cucumber: BDD style (ongoing)

**Project Structure:**

src/test/java:

- driver
- listener
- pom.pages
- pom.testcases
- util

src/test/resources:
- app
  - XXX.apk
- config
  - config.properties
  - initTestDevice.properties
- allure.properties
- log4j.properties

output:
- logs
- screenshots

pom.xml

testng.xml
