package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BaseView {

    private static final String XPATHASSERTUSERRECOVERYPASSWORD =
            "//div[@class='mira-bread_crumbs-caption caption'] //div[contains(text(), 'Фомина Елена Сергеевна')]";
    private static final String XPATHCHANGEBUTTON = "//div[text()='Cменить']";


    public MessagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = XPATHASSERTUSERRECOVERYPASSWORD)
    public WebElement currentUserData;

    public By currentUserDataLocator = By.xpath(XPATHASSERTUSERRECOVERYPASSWORD);

    @FindBy(xpath = XPATHCHANGEBUTTON)
    public WebElement changebutton;


}
