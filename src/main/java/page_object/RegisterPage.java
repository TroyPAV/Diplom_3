package page_object;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final String NAME = RandomStringUtils.randomAlphabetic(10);
    private final String EMAIL = "a" + RandomStringUtils.randomAlphanumeric(5) + "@example.com";
    private final String PASSWORD = "p@ssw0rd";

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локаторы элементов страницы регистрации пользователя
     */

    //URL страницы регистрации
    private final String registerPageURL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор надписи Регистрация
    private By registerText = By.xpath(".//div//h2[text()= 'Регистрация']");

    //Локатор поля ввода Имени
    @FindBy(xpath = ".//fieldset[1]//input")
    private WebElement nameField;

    //Локатор поля ввода Почты
    @FindBy(xpath = ".//fieldset[2]//input")
    private WebElement emailField;

    //Локатор поля ввода Пароля
    @FindBy(xpath = ".//fieldset[3]//input")
    private WebElement passwordField;

    //Локатор кнопки Зарегистрироваться
    @FindBy(xpath = ".//button[text() = 'Зарегистрироваться']")
    private WebElement registerButton;

    //Локатор надписи "Некорректный пароль"
    @FindBy(xpath = ".//div//p[text()= 'Некорректный пароль']")
    private WebElement passErrorText;

    //Локатор ссылки перехода на страницу авторизации
    @FindBy(linkText = "Войти")
    private WebElement loginLink;


    /**
     * Методы для взаимодействия с элементами страницы регистрации пользователя
     */

    //Метод вызова страницы регистрации
    public RegisterPage getRegisterPage() {
        driver.get(registerPageURL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(registerText));
        return this;
    }

    //Метод ввода символов в поле Имя
    public RegisterPage setNameField() {
        nameField.sendKeys(NAME);
        return this;
    }

    //Метод ввода символов в поле Почта
    public RegisterPage setEmailField() {
        emailField.sendKeys(EMAIL);
        return this;
    }

    //Метод ввода символов в поле Пароль
    public RegisterPage setPasswordField() {
        passwordField.sendKeys(PASSWORD);
        return this;
    }

    //Метод ввода некорректного пароля из 5-и символов
    public RegisterPage setIncorrectPassword() {
        passwordField.sendKeys(RandomStringUtils.randomAlphanumeric(5));
        return this;
    }

    //Метод клика на кнопку регистрации
    public RegisterPage registerButtonClick() {
        registerButton.click();
        return this;
    }

    //Метод проверки отображения сообщения об ошибке поля Пароль
    public RegisterPage passErrorIsDisplayed() {
        Assert.assertTrue(passErrorText.isDisplayed());
        return this;
    }

    //Метод клика ссылки на страницу авторизации
    public RegisterPage loginLinkClick() {
        loginLink.click();
        return this;
    }

    //Метод заполнения формы регистрации
    public void registration() {
        setNameField();
        setEmailField();
        setPasswordField();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", registerButton);
        registerButtonClick();
    }
}
