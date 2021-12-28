package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RecoveryMessagePage extends BaseView {
    private static final String XPATHLINKRECOVERYPASSWORD =
            "//div[@style='width: 100%; max-width: 1100px; word-break: break-all;']/a";

    private static final String XPATHASSERTRECOVERYMESSAGEPAGE = "//div[contains(text(), 'Восстановление пароля')]";

    public RecoveryMessagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = XPATHLINKRECOVERYPASSWORD)
    public WebElement recoveryPasswordLink;

    @FindBy(xpath = XPATHASSERTRECOVERYMESSAGEPAGE)
    public WebElement assertrecoverypage;

    public By assertrecoverypageLocator = By.xpath(XPATHASSERTRECOVERYMESSAGEPAGE);

    public MessagePage clickLinkRecoveryPassword() {
        recoveryPasswordLink.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(new MessagePage(driver).currentUserDataLocator));
        return new MessagePage(driver);
    }

}
