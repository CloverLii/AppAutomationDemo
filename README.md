Application UI automation framework

Java: Appium + TestNG + log (slf4i&log4j) + report (Allure) + assertion (junit5 & testng) + maven + PO Model

Structure:
-src/test/java:
    -driver
    -listener
    -pageobj.page
    -pageobj.locator
    -pageobj.data
    -testcases
    -util
-src/test/resources:
    -config: 
        -config.properties
        -log4j.properteis
-application
-ouput:
    -screenshot
    -logs
    -reports

Features —— still ongoing:
-output log files using slf4j+log4j
-save screenshot of failed cases
-retry failed methods up to 3 times
-configure global parameters using properties file
-configure execution using testing.xml
-check group of values using junit.jupiter.api.Assertions.assertAll()
-generate readable reports

