# README #

**Atlassian Readme**

**Prerequisites:**

I have run my tests on Chrome [since my selenium version was not supported by my latest Firefox]
The framework has support for running on Firefox browser also.

Download the ChromeDriver from the following link: https://sites.google.com/a/chromium.org/chromedriver/downloads
Add the path to “ChromeDriver” in the file “src/main/java/atlassian/configuration/properties/stage_config.properties” as the value for key “chromeDriverPath”

eg. chromeDriverPath=/Users/rajeshkalloor/Downloads/chromedriver


**Test Data :**

uiBaseURL : https://rajeshkalloor.atlassian.net

2 User accounts :
Rajesh Kalloor [administrator] 
Rajesh Kalloor Test [user invited by the administrator]

I have used the above 2 accounts to test the “Restrictions” feature.

The first test “createNewPageAndSetRestrictions” in the class “src/test/atlassian/UITest.java” tests the creation of new pages
We log in as the administrator and create 3 new pages with the following 3 restriction levels:
- No restrictions
- Editing restricted
- Viewing and Editing restricted
We assert that each of the pages have the correct title and content and we also store it in a List<PageData> for later use.

The second test “testRestrictions in the same class tests the restrictions that are set in the previous test
This method dependsOn the previous method to complete successfully since it uses the pages created in the previous method.
We log in as the test user and login to each of the 3 pages created in the first method. We assert the following
- The user is able to edit the page created with “no restrictions”
- The user is not able see the edit button for the page created with “editing restricted”
- The user is not able to view the page created with “viewing and editing restricted”


**How to run the tests:**

Method 1:
Open the project in an IDE of choice and run the class “UITest.java”

Method 2:
Navigate to the project home on command line and run it using maven.
“mvn test”