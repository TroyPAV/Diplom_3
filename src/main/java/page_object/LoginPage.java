package page_object;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локаторы элементов страницы авторизации пользователя
     */

    //URL страницы авторизации
    private final String loginPageURL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор надписи Вход
    private By enterText = By.xpath(".//div//h2[text()= 'Вход']");

    //Локатор поля ввода Почты
    @FindBy(xpath = ".//fieldset[1]//input")
    private WebElement emailField;

    //Локатор поля ввода Пароля
    @FindBy(xpath = ".//fieldset[2]//input")
    private WebElement passwordField;

    //Локатор кнопки "Войти"
    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement enterButton;

    /**
     * Методы для взаимодействия с элементами страницы авторизации пользователя
     */

    //Метод вызова страницы авторизации
    public LoginPage getLoginPage() {
        driver.get(loginPageURL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(enterButton));
        return this;
    }

    //метод проверки отображения страницы авторизации
    public LoginPage loginPageIsDisplayed() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(enterText));
        Assert.assertEquals(loginPageURL, driver.getCurrentUrl());
        return this;
    }

    //Метод ввода символов в поле Email
    public LoginPage setEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    //Метод ввода символов в поле Пароль
    public LoginPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    //Метод авторизации пользователя
    public LoginPage login(String email, String password){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(enterButton));
        setEmailField(email);
        setPasswordField(password);
        enterButton.click();
        return this;
    }
}
