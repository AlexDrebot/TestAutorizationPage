package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuView extends BaseView {

    private static final String XPATHASSERTMAINPAGE = "//span[contains(text(), 'Главная страница')]";
    private static final String XPATHASSERTMAINMENUVIEW = "//div[@class='mira-wrap-header']";
    private static final String XPATHMIRALOGO = "//img[@data-mira-id='Image11']";
    private static final String XPATHUSETUPBLOCK = "//div[@data-mira-id='Template29']";
    private static final String XPATHBUTТLOGOUT = "//span[contains(text(),'Профиль')]";
    private static final String XPATHNOTIFICATIONBTN = "//div[@class='mira-notification-info-widget-notifications ']";

    public MainMenuView(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = XPATHASSERTMAINMENUVIEW)
    public WebElement miraPolisHomePageText;

    public By miraPolisMainPageLocator = By.xpath(XPATHASSERTMAINMENUVIEW);

    @FindBy(xpath = XPATHMIRALOGO)
    public WebElement miraLogo;

    public By miraLogoLocator = By.xpath(XPATHMIRALOGO);

    @FindBy(xpath = XPATHNOTIFICATIONBTN)
    public WebElement notificationbtn;

    public NotificationView clicknotificationbtn(){
        notificationbtn.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new NotificationView(driver).notificateTextLocator));
        return new NotificationView(driver);
    }
}
