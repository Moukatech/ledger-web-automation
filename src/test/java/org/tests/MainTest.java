package org.tests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Reporter;

public class MainTest {
    private String URL=System.getProperty("url");
    @Test
    public void Test1_userLogin() {
        Reporter.log("Opening browser and running login");
        open(URL);
        $(By.xpath("//*[text()='Sign in']")).click();
        $(By.xpath("//*/fieldset[1]/input")).setValue("lewismocha@gmail.com");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Test123");
        $(By.xpath("//div//button")).click();
        Reporter.log("Login test has completed");
    }

    @Test
    public void Test2_addNewPost() {
        //open("https://react-redux.realworld.io/");
        Reporter.log("Adding a new post");
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
    }

    @Test
    public void Test3_addPostFavorites() {
        Reporter.log("Adding post to favorite");
        $(By.cssSelector(".user-pic")).click(); // clicks on the user profile icon
        $( By.cssSelector(".ion-heart")).click(); // clicks on th e
    }

    @Test

    public void Test4_deleteApost() {
        $(By.xpath("//*/div/div/div[2]/div/div/div[2]/div[1]/a/h1")).click(); // selects a post to be deleted.
    }
}
