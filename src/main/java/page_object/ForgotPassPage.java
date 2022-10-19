package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassPage {

    private final WebDriver driver;

    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локаторы элементов страницы авторизации пользователя
     */

    //URL страницы авторизации
    private final static String FORGOT_PASS_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор кнопки "Восстановить"
    @FindBy(xpath = ".//button[text()= 'Восстановить']")
    private WebElement restoreButton;

    //Локатор ссылки перехода на страницу авторизации
    @FindBy(linkText = "Войти")
    private WebElement loginLink;

    /**
     * Методы для взаимодействия с элементами страницы авторизации пользователя
     */

    //Метод вызова страницы авторизации
    public ForgotPassPage getForgotPassPage() {
        driver.get(FORGOT_PASS_PAGE_URL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(restoreButton));
        return this;
    }

    //Метод клика ссылки на страницу авторизации
    public ForgotPassPage loginLinkClick() {
        loginLink.click();
        return this;
    }
}
