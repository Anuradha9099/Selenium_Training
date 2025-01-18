package com.mail;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Code;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetPasswordTest {
    WebDriver webDriver;
    MailosaurClient mailosaur;
    SearchCriteria criteria;
    MessageSearchParams params;
    long testStart;

    String apiKey = "fwZ5gScvW80aXJLVapJTYInkPvPSW35O";
    String serverId = "48jhtgoe";
    String serverDomain = serverId + ".mailosaur.net";

    @BeforeMethod
    public void beforeMethod() throws MailosaurException {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        mailosaur = new MailosaurClient(apiKey);
        criteria = new SearchCriteria();
        params = new MessageSearchParams();
        mailosaur.messages().deleteAll(serverId);

        testStart = System.currentTimeMillis();
    }

    @AfterMethod
    public void afterMethod() throws MailosaurException {
        webDriver.quit();
        params = new MessageSearchParams();
        params.withServer(serverId)
                .withReceivedAfter(testStart);

        //mailosaur.messages().deleteAll(serverId);
    }

    @Test
    public void testResetPasswordWithEmail() throws MailosaurException, IOException {
        webDriver.get("https://example.mailosaur.com/password-reset");

        Message message = getMessage();
        Assert.assertEquals(message.subject(), "Set your new password for ACME Product");

        String linkText = message.html().links().get(0).text().replace("\n", "").replace("                        ", " ").trim();
        Assert.assertEquals(linkText, "Set my password"); //Verify the inner content of HTML content

        String resetPasswordURL = message.html().links().get(0).href(); //get the link within the email HTML content
        Assert.assertEquals(resetPasswordURL, "https://example.mailosaur.com/set-password");
        webDriver.get(resetPasswordURL); // Navigate to the URL

        //Resetting the password
        webDriver.findElement(By.id("password")).sendKeys("PTL@#321");
        webDriver.findElement(By.id("confirmPassword")).sendKeys("PTL@#321");
        webDriver.findElement(By.xpath("//button[text()='Reset my password']")).click();

        Assert.assertEquals(webDriver.findElement(By.tagName("h1")).getText(), "Your new password has been set!");

    }

    @Test
    public void testResetPasswordWithOTP() throws MailosaurException, IOException {
        webDriver.get("https://example.mailosaur.com/otp");

        Message message = getMessage();
        Assert.assertEquals(message.subject(), "Here is your access code for ACME Product");

        Code firstCode = message.html().codes().get(0);
        Assert.assertEquals(message.html().codes().size(), 1);
        Assert.assertTrue(firstCode.value().matches("\\d{6}")); //Assert number of 6-digit number
    }

//    private Message getMessage() throws IOException, MailosaurException {
//        String email = mailosaur.servers().generateEmailAddress(serverId); //generate email automatically
//
//        webDriver.findElement(By.id("email")).sendKeys(email);
//        webDriver.findElement(By.xpath("//button['submit']")).click();
//
//        params.withServer(serverId);
//        criteria.withSentTo(email);
//        Message message = mailosaur.messages().get(params, criteria);
//
//        Assert.assertNotNull(message);
//
//        Assert.assertEquals(message.to().get(0).email(), email); //verify the email address in TO
//        Assert.assertEquals(message.from().get(0).email(), "noreply@example.mailosaur.net"); //verify the email address in FROM
//        return message;
//    }
}
