package org.tests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class MainTest {
    private String URL=System.getProperty("url");
    ExtentSparkReporter htmlReporter;
    ExtentReports extent;
    @BeforeSuite
    public void setUp() {
        htmlReporter = new ExtentSparkReporter("index.html");
        //create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void loginValidations() {
        ExtentTest test1 = extent.createTest("Login Validation", "Test to validate user cannot login with the wrong credentials");
        test1.log(Status.INFO, "Starting Test");
        open(URL);
        $(By.xpath("//*[text()='Sign in']")).click();
        $(By.xpath("//*/fieldset[1]/input")).setValue("lewismocha@gmail.com");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Test12333");
        $(By.xpath("//div//button")).click();
        SelenideElement errorMessage = $(By.xpath("//*[text()='email or password']"));
        if (errorMessage != null) {
            test1.pass("Test Passed");
        } else {
            test1.fail("Test Failed");
        }
    }

    @Test
    public void Test1_userLogin() {
        ExtentTest test1 = extent.createTest("User Login", "Test to validate user can login with the correct credentials");
        test1.log(Status.INFO, "Starting Test");
        open(URL);
        $(By.xpath("//*[text()='Sign in']")).click();
        $(By.xpath("//*/fieldset[1]/input")).setValue("lewismocha@gmail.com");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Test123");
        $(By.xpath("//div//button")).click();
        SelenideElement feedTitle= $(By.xpath(".//*/div/div/div/div/div[1]/div[1]/ul/li[1]/a"));
        if (feedTitle!= null){
            test1.pass("Login test Passed");
        }else {
            test1.fail("Login test Failed");
        }
    }

    @Test
    public void Test2_addNewPost() {
        //open("https://react-redux.realworld.io/");
        ExtentTest test2 = extent.createTest("Adding a new post", "verify a user can add a post successfully");
        SelenideElement publishButton =$(By.xpath("//*/button[contains(text(),'Publish Article')]"));
        SelenideElement blogTitle = $(By.xpath("//*/div/div/div[1]/div/h1"));
        $(By.xpath("//*/a[contains(text(),'New Post')]")).click();
        if(publishButton!= null){
            test2.log(Status.INFO,"Page loaded successfully");
        }else{
            test2.fail("Page failed to load");
        }
        $(By.xpath("//*/fieldset[1]/input")).setValue("My Test blog to write");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Mentor young QA members");
        $(By.xpath("//*/fieldset[3]/textarea")).setValue("Provide Materials to learn for the team /n Provide knowledge sharing sessions");
        $(By.xpath("//*/fieldset[4]/input")).setValue("#automation for all");
        publishButton.click();
        blogTitle.should(appear);
        if (blogTitle.isDisplayed()) {
            test2.pass("Added post successfully");
        }else {
            test2.fail("Post was not created");
        }
    }

    @Test
    public void Test3_addPostFavorites() {
        ExtentTest test3 = extent.createTest("Adding post to favorite", "Verify a user can add a post to favorites");
        SelenideElement myArticals = $(By.partialLinkText("My Articles"));
        $(By.cssSelector(".user-pic")).click(); // clicks on the user profile icon
        if (myArticals!= null) {
            test3.log(Status.INFO,"Correct page was loaded");
        }else {
            test3.fail("Page failed to load ");
        }
        $(By.cssSelector(".ion-heart")).click(); // clicks on the favorites Icon
        test3.pass("Post added to favorite successfully");
    }

    @Test

    public void Test4_deletePost() {
        ExtentTest test4 = extent.createTest("Delete post", "Verify a user can delete a post");
        SelenideElement myArticals = $(By.partialLinkText("My Articles"));
        $(By.cssSelector(".user-pic")).click(); // clicks on the user profile icon
        if (myArticals!= null) {
            test4.log(Status.INFO,"Correct page was loaded");
        }else {
            test4.fail("Page failed to load ");
        }
        $(By.xpath("//*/div/div/div[2]/div/div/div[2]/div[1]/a/h1")).click(); // selects a post to be deleted.
        SelenideElement deleteButton = $(By.xpath("//*/button[text()=' Delete Article']"));
        deleteButton.click();
        SelenideElement feedTitle= $(By.partialLinkText("Your Feed"));
        if (feedTitle!= null){
            test4.pass("Post Deleted Successfully");
        }else {
            test4.fail("Failed to delete the first post");
        }
    }



    @AfterSuite
    public void afterClass() {
        extent.flush();
    }
}
