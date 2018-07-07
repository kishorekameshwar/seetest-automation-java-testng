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

```
# /src/main/java/resources/seetest.properties
access.key = <your copy>

``` 
2. Upload the eribank application to your project
Download the Android app : https://experitest.s3.amazonaws.com/eribank.apk
Download the iOS app : https://experitest.s3.amazonaws.com/EriBank.ipa

Go to the cloud "Mobile Application Center" and upload both apps 
https://cloud.seetest.io/index.html#/applications

3. run the testng.xml file

Right click on the file and run from IntelliJ \ Eclipse 
Or use the command line : 

```
gradlew runTests
```

### How to change to your own application

1. Upload you application to the cloud

(review step two in guide)

2. Change the android application name or iOS application name in the seetest.properties file

```
# /src/main/java/resources/seetest.properties
ios.app.name = com.company.app
android.app.name = com.company.app/.appActivity

``` 

3. Copy your project base directory to the app properties file
```
# /src/main/java/resources/seetest.properties
# you can place a full path e.g C:\\projectbasedir OR ~/projectbasedir
project.base.directory="SeeTestProject"
```

4. Modify the tests 

change the @Test methods at AndroidApplicationTest \ IOSApplicationTest

you can paste the code you've exported from SeeTestAutomation 


