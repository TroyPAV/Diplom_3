import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page_object.*;
import user.*;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    User user;
    UserClient userClient;
    UserCredentials creds;
    static WebDriver driver;

    @Before
    public void setup() {
        user = User.getUser();
        userClient = new UserClient();
        userClient.create(user);
        creds = UserCredentials.from(user);

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
        userClient.tearDown(creds);
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа в аккаунт через кнопку «Войти в аккаунт» на главной станице.")
    public void accountEnterMainPageAccountEnterButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .getMainPage()
                .accountEnterButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Вход по кнопке «Личный кабинет» на главной")
    @Description("Проверка входа в аккаунт через кнопку «Личный кабинет» на главной станице.")
    public void accountEnterMainPageProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .getMainPage()
                .profileButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти» в форме регистрации")
    @Description("Проверка входа в аккаунт через кнопку «Войти» в форме регистрации.")
    public void accountEnterRegisterPageLoginLink() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage
                .getRegisterPage()
                .loginLinkClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти» в форме восстановления пароля")
    @Description("Проверка входа в аккаунт через кнопку «Войти» в форме восстановления пароля.")
    public void accountEnterForgotPassPageLoginLink() {
        ForgotPassPage forgotPassPage = new ForgotPassPage(driver);
        forgotPassPage
                .getForgotPassPage()
                .loginLinkClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.checkOrderButton();
    }
}
