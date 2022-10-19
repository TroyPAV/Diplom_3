package page_object;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локаторы элементов страницы авторизации пользователя
     */

    //URL страницы аккаунта
    private final static String ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account";

    //URL страницы профиля пользователя
    private final static String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

   //Локатор текста "В этом разделе вы можете изменить свои персональные данные"
    private By profileText = By.xpath(".//div//p[text()= 'В этом разделе вы можете изменить свои персональные данные']");

    //Локатор кнопки "Конструктор"
    @FindBy(xpath = ".//ul[@class = 'AppHeader_header__list__3oKJj']//a")
    private WebElement constructorButton;

    //Локатор кнопки "Выход"
    @FindBy(xpath = ".//button[text()= 'Выход']")
    private WebElement quitButton;

    /**
     * Методы для взаимодействия с элементами страницы авторизации пользователя
     */

    //Метод вызова страницы авторизации
    public ProfilePage getProfilePage() {
        driver.get(ACCOUNT_PAGE_URL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(profileText));
        return this;
    }

    //метод проверки отображения страницы профиля
    public ProfilePage profilePageIsDisplayed() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(profileText));
        Assert.assertEquals(PROFILE_PAGE_URL, driver.getCurrentUrl());
        return this;
    }

    //Метод клика на кнопку "Конструктор"
    public ProfilePage constructorButtonClick() {
        constructorButton.click();
        return this;
    }

    //Метод клика на кнопку "Выход"
    public ProfilePage quitButtonClick() {
        quitButton.click();
        return this;
    }
}
