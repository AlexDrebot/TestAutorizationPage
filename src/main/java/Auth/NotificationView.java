package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationView extends BaseView {
    private static final String XPATHNOTIFICATEASSERTTEXT = "//div[contains(text(), 'Мои уведомления')]";
    // Длинный xpath для получения первого уведомления о восстановлении пароля после теста на отправку пароля
    private static final String XPATHMESSAGERECOVERYPASSWORD =
            "//tr[@data-mira-grid-row='0']//span[@class='mira-grid-cell-preview']//a";

    public NotificationView(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = XPATHNOTIFICATEASSERTTEXT)
    public WebElement notificateText;

    public By notificateTextLocator = By.xpath(XPATHNOTIFICATEASSERTTEXT);

    @FindBy(xpath = XPATHMESSAGERECOVERYPASSWORD)
    public WebElement messageRecoveryPassword;

    public RecoveryMessagePage clickMessageWithLinkRecoveryPassword() {
        messageRecoveryPassword.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (new RecoveryMessagePage(driver).assertrecoverypageLocator));
        return new RecoveryMessagePage(driver);
    }


}
