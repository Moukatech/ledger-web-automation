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
        htmlReporter = new ExtentSparkReporter("testReport/index.html");
        //create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
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
        test1.pass("Login test Passed");
    }

    @Test
    public void Test2_addNewPost() {
        //open("https://react-redux.realworld.io/");
        ExtentTest test2 = extent.createTest("Adding a new post", "verify a user can add a post successfully");
        SelenideElement publishButton =$(By.xpath("//*/button[contains(text(),'Publish Article')]"));
        SelenideElement blogTitle = $(By.xpath("//*/div/div/div[1]/div/h1"));
        $(By.xpath("//*/a[contains(text(),'New Post')]")).click();
        publishButton.should(appear);
        $(By.xpath("//*/fieldset[1]/input")).setValue("My fourth blog to write");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Mentor young QA members");
        $(By.xpath("//*/fieldset[3]/textarea")).setValue("Provide Materials to learn for the team /n Provide knowledge sharing sessions");
        $(By.xpath("//*/fieldset[4]/input")).setValue("#automation for all");
        publishButton.click();
        blogTitle.should(appear);
        test2.pass("Added post successfully");
    }

    @Test
    public void Test3_addPostFavorites() {
        ExtentTest test3 = extent.createTest("Adding post to favorite", "Verify a user can add a post to favorites");
        $(By.cssSelector(".user-pic")).click(); // clicks on the user profile icon
        $( By.cssSelector(".ion-heart")).click(); // clicks on the favorites Icon
        test3.pass("Post added to favorite successfully");
    }

    @Test

    public void Test4_deletePost() {
        ExtentTest test4 = extent.createTest("Delete post", "Verify a user can delete a post");
        $(By.xpath("//*/div/div/div[2]/div/div/div[2]/div[1]/a/h1")).click(); // selects a post to be deleted.
        $(By.partialLinkText("Delete Article")).click();
        test4.pass("Post Deleted Successfully");
    }

    @AfterSuite
    public void afterClass() {
        extent.flush();
    }
}
