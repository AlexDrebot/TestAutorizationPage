package Auth;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Config.Configuration.XPATHSENDBUTTON;


public class AuthView extends BaseView {


    //Локаторы XPATH
    private static final String XPATHINPUTNAME = "//input[@name='user']";
    private static final String XPATHINPUTPASSWORD = "//input[@name='password']";
    private static final String XPATHSHOWPASSWORD = "//button[@id='show_password']";
    private static final String XPATHFORGOTPASSWORD = "//a[@class='mira-default-login-page-link']";
    private static final String XPATHLOCATEAUTHPAGE = "//div[contains(text(), 'Вход в систему')]";


    @FindBy(xpath = XPATHINPUTNAME)
    public WebElement inputlogin;

    @FindBy(xpath = XPATHINPUTPASSWORD)
    public WebElement inputpasword;

    @FindBy(xpath = XPATHSHOWPASSWORD)
    public WebElement showpasswordbutton;

    @FindBy(xpath = XPATHFORGOTPASSWORD)
    public WebElement forgotpasswordbutton;

    @FindBy(xpath = XPATHSENDBUTTON)
    public WebElement submitbutton;

    @FindBy(xpath = XPATHLOCATEAUTHPAGE)
    public WebElement locateAuthPage;

    public By locateAuthPageLocator = By.xpath(XPATHLOCATEAUTHPAGE);


    public AuthView(WebDriver driver) {
        super(driver);
    }


    public AuthView fillinputlogin(String login) {
        inputlogin.sendKeys(login);
        return this;
    }

    public AuthView fillinputpassword(String password) {
        inputpasword.sendKeys(password);
        return this;
    }

    public ForgetPasswordView fillinputforgotpassword() {
        forgotpasswordbutton.click();
        return new ForgetPasswordView(driver);
    }

    public AuthView fillinputshowpassword(String showpassword) {
        showpasswordbutton.sendKeys(showpassword);
        return this;
    }
    // Данный метод здесь находится для возвращения AuthPage при получении уведомления о неккоректных данных при вводе
    public AuthView clickincorrectlogin() {
        submitbutton.click();
        return this;
    }

    public MainMenuView clicksubmit() {
        submitbutton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new MainMenuView(driver).miraPolisMainPageLocator));
        return new MainMenuView(driver);
    }

    public MainMenuView login(String login, String password) {
        inputlogin.sendKeys(login);
        inputpasword.sendKeys(password);
        submitbutton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(new MainMenuView(driver).miraPolisMainPageLocator));
        return new MainMenuView(driver);
    }

}
