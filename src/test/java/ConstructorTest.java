import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page_object.MainPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTest {

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
    @DisplayName("Переход к разделу Булки в конструкторе")
    @Description("Проверка перехода на раздел Булки при нажатии названия раздела в конструкторе")
    public void checkBunSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .getMainPage()
                .bunScrollCheck();
    }

    @Test
    @DisplayName("Переход к разделу Соусы в конструкторе")
    @Description("Проверка перехода на раздел Соусы при нажатии названия раздела в конструкторе")
    public void checkSauceSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .getMainPage()
                .sauceScrollCheck();
    }

    @Test
    @DisplayName("Переход к разделу Начинки в конструкторе")
    @Description("Проверка перехода на раздел Начинки при нажатии названия раздела в конструкторе")
    public void checkFillingSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .getMainPage()
                .fillingScrollCheck();
    }
}
