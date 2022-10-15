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
import page_object.MainPage;
import page_object.ProfilePage;
import user.*;

import java.util.concurrent.TimeUnit;

public class NavigationTest {

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

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .getLoginPage()
                .login(user.getEmail(), user.getPassword());
    }

    @After
    public void teardown() {
        userClient.tearDown(creds);
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет авторизованного пользователя.")
    public void checkNavigationToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.profilePageIsDisplayed();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка перехода из личный кабинета авторизованного пользователя в конструктор на главной странице.")
    public void checkPersonalAccountToConstructorNavigation() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage
                .getProfilePage()
                .constructorButtonClick();
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageIsDisplayed();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода авторизованного пользователя из аккаунта.")
    public void checkQuitAccount() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage
                .getProfilePage()
                .quitButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageIsDisplayed();
    }
}
