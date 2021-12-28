package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Config.Configuration.XPATHSENDBUTTON;

public class ForgetPasswordView extends BaseView {

    // Ссылка для перехода на страницу авторизации
    private static final String XPATHBUTTONBACKTOSYSTEM = "//div[contains(text(),'Назад')]";
    //Локатор для ввода логина при восстановлении пароля
    public static final String XPATHINSERTEMAILORLOGIN = "//input[@name='loginOrEmail']";
    //Локатор идентификации "Восстановление пароля"
    public static final String XPATHASSERTRECOVERYPASS = "//div[@class='info-title']";
    //Локатор заголовка содержащего текс подсказке как восстановить пароль
    private static final String XPATHASSERTRECOVERYPASSHINT = "//div[@class='forgot-password-label']";
    // Локатор всплывающего окна
    private static final String XPATHNOTIFICATIONSUCCESS = "//div[@class='success']";
    private static final String XPATHUNNOTIFICATIONSUCCESS = "//div[@class='alert']";


    public ForgetPasswordView(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = XPATHNOTIFICATIONSUCCESS)
    public WebElement notificationSuccess;

    public By notificationSuccessLocator = By.xpath(XPATHNOTIFICATIONSUCCESS);

    @FindBy(xpath = XPATHUNNOTIFICATIONSUCCESS)
    public WebElement unNotificationSuccess;

    public By unNotificationSuccessLocator = By.xpath(XPATHUNNOTIFICATIONSUCCESS);



    @FindBy(xpath = XPATHASSERTRECOVERYPASS)
    public WebElement recoveryPassword;

    public By recoveryPasswordLocator = By.xpath(XPATHASSERTRECOVERYPASS);

    @FindBy(xpath = XPATHASSERTRECOVERYPASSHINT)
    public WebElement recoveryPasswordHint;

    public By recoveryPasswordHintLocator = By.xpath(XPATHASSERTRECOVERYPASSHINT);

    @FindBy(xpath = XPATHINSERTEMAILORLOGIN)
    public WebElement inputlogin;

    @FindBy(xpath = XPATHBUTTONBACKTOSYSTEM)
    public WebElement backButton;

    @FindBy(xpath = XPATHSENDBUTTON)
    public WebElement submitbutton;

    public ForgetPasswordView inputlogin(String login) {
        inputlogin.sendKeys(login);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new ForgetPasswordView(driver).recoveryPasswordLocator));
        return this;
    }

    public ForgetPasswordView clickSubmitBitton() {
        submitbutton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new ForgetPasswordView(driver).notificationSuccessLocator));
        return this;
    }

    public ForgetPasswordView clickSubmitBittonWithIncorrectData() {
        submitbutton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new ForgetPasswordView(driver).unNotificationSuccessLocator));
        return this;
    }

    public AuthView clickBackButton() {
        backButton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new AuthView(driver).locateAuthPageLocator));
        return new AuthView(driver);
    }


}


