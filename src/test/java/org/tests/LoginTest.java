package org.tests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void Test1userLogin() {
        open("https://react-redux.realworld.io/");
        $(By.xpath("//*[text()='Sign in']")).click();
        $(By.xpath("//*/fieldset[1]/input")).setValue("lewismocha@gmail.com");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Test123");
        $(By.xpath("//div//button")).click();
    }

    @Test
    public void Test2addNewPost() {
        //open("https://react-redux.realworld.io/");
        SelenideElement publishButton =$(By.xpath("//*[@id='main']//button[contains(text(),'Publish Article')]"));
        SelenideElement blogTitle = $(By.xpath("//*[@id='main']/div/div/div[1]/div/h1"));
        $(By.xpath("//*[@id='main']//a[contains(text(),'New Post')]")).click();
        publishButton.should(appear);
        $(By.xpath("//*/fieldset[1]/input")).setValue("My Second blog to write");
        $(By.xpath("//*/fieldset[2]/input")).setValue("Mentor young QA members");
        $(By.xpath("//*/fieldset[3]/textarea")).setValue("Provide Materials to learn for the team /n Provide knowledge sharing sessions");
        $(By.xpath("//*/fieldset[4]/input")).setValue("#automation for all");
        publishButton.click();
        blogTitle.should(appear);
    }

    @Test
    public void Test3Favorites() {
        $(By.xpath("//*[@id='main']/div/nav/div/ul/li[4]/a")).click();
        $(By.xpath("//*[@id='main']/div/div/div[2]/div/div/div[2]/div[1]/div/div[2]/button")).click();
    }

}
