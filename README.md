# seetest.io - SeeTestAutomation Grid Project
## Java - TestNG sample project 

In this example project we will execute tests created in SeeTestAutomation 
against hosted devices located in seetest.io cloud

We've tried to keep this example simple, but still covering to following : 

1. Integration with seetest.io cloud 
2. Running tests on iOS \ Android in parallel

### Steps to run demo test

1. Clone this git repository
```
git clone 
```
2. Obtain access key from seetest.io cloud

https://docs.seetest.io/display/SEET/Execute+Tests+on+SeeTest+-+Obtaining+Access+Key

note :  you need to have a valid subscription or a trial to run this test (Trial \ paid)

After you have obtained a valid key change the seetest.properties file with your own copy

```access.key = <your copy>``` 

3. run the testng.xml file

Right click on the file and run from IntelliJ \ Eclipse 
Or use the command line : 

```$xslt
gradlew runTests
```