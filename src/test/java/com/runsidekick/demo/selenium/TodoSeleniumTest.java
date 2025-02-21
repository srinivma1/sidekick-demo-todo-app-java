package com.runsidekick.demo.selenium;

import com.runsidekick.demo.ContextInitializedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author tolgatakir
 */
@Testcontainers
@SqlGroup({
@Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TodoSeleniumTest extends ContextInitializedTest {

    @Container
    private final BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
    .withCapabilities(new ChromeOptions());

    @Test
    void testOpenTodoPage() {
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get("http://" + getHostAddress() + ":" + localPort);
        List<WebElement> elements = driver.findElementsByTagName("h1");
        chrome.getWebDriver().getScreenshotAs(OutputType.FILE);
        assertThat(elements).extracting("text").containsOnly("todos");
    }

    @Test
    void testAddTodo() {
        RemoteWebDriver driver = chrome.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("http://" + getHostAddress() + ":" + localPort);
        driver.getScreenshotAs(OutputType.BYTES);
        List<WebElement> elements = driver.findElementsByClassName("new-todo");
        assertThat(elements).isNotEmpty();
        WebElement inputElement = elements.get(0);

        inputElement.sendKeys("Test Todo");
        inputElement.sendKeys(Keys.ENTER);
        By xpath = By.xpath("/html/body/div/section/section/ul/li[1]/div/label");
        List<WebElement> assertionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
        driver.getScreenshotAs(OutputType.BASE64);
        assertThat(assertionElements).extracting("text").containsOnly("Test Todo");

        inputElement.sendKeys("Test Todo2");
        inputElement.sendKeys(Keys.ENTER);
        xpath = By.xpath("/html/body/div/section/section/ul/li[2]/div/label");
        assertionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
        driver.getScreenshotAs(OutputType.FILE);
        assertThat(assertionElements).extracting("text").containsOnly("Test Todo2");
    }

    private static String getHostAddress() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("nix") >= 0
        || os.indexOf("nux") >= 0
        || os.indexOf("aix") > 0) {
            return "172.17.0.1";
        } else {
            return "host.docker.internal";
        }
    }
}
