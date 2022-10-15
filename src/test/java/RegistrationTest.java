import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page_object.LoginPage;
import page_object.RegisterPage;

import java.util.concurrent.TimeUnit;

public class RegistrationTest{

    static WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        //driver = new ChromeDriver(options);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Проверка успешной регистрации нового пользователя.")
    public void checkRegistrationPositiveFlow() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage
                .getRegisterPage()
                .registration();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageIsDisplayed();
    }

    @Test
    @DisplayName("Появление сообщения об ошибке для некорректного пароля")
    @Description("Проверка появления сообщения об ошибке во время регистрации при вводе некорректного пароля меньше 6-и символов.")
    public void checkPasswordFieldErrorText() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage
                .getRegisterPage()
                .setIncorrectPassword()
                .registerButtonClick()
                .passErrorIsDisplayed();
    }
}
