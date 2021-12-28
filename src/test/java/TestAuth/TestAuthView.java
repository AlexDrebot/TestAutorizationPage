package TestAuth;

import Auth.AuthView;
import Auth.ForgetPasswordView;
import Auth.MainMenuView;
import Auth.MessagePage;
import org.junit.jupiter.api.*;


import static org.hamcrest.MatcherAssert.assertThat;

import static Config.Configuration.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAuthView extends BaseTestAuth {

    @BeforeEach
    public void goToPageMira() {
        driver.get(MIRAURL);
    }

    /**
     * 1 Тест кейс для проверки возможности входа в систему с корректными данными
     * Для подтвердения авторизации взят элемент который появляется при загрузки главной страницы
     */
    @Test
    @Order(1)
    void loginTest() {
        new AuthView(driver)
                .fillinputlogin(CORECTUSERNAME)
                .fillinputpassword(CORRECTUSERPASSWORD)
                .clicksubmit();
        // Проверка на наличие элемента на главное странице после вхоа

        assertThat(new MainMenuView(driver).miraPolisHomePageText, isDisplayed());
    }

    /**
     * 2 Тест кейс для проверки отсутствия возможности войти в систему вводя некорректные данные
     * Для подтвердеждения корректных действий системы было выполнено сравнения лжидаемого текста
     * с текстом внутри уведомления alert
     */
    @Test
    void loginTestWithIncorrectData() {
        new AuthView(driver)
                .fillinputlogin(INCORRECTUSERNAME)
                .fillinputpassword(INCORRECTUSERPASSWORD)
                .clickincorrectlogin();
        // Проверка на на текста внутри alert при вводе неккоректных данных
        assertTrue(driver.switchTo().alert().getText().contains("Неверные данные для авторизации"));
    }

    /**
     * 3 Тест кейс для проверки возможности перейти на страницу восстановления пароля
     * Для подтверждения корректных действий системы было выполнено сравнение на наличие ожидаемого текста в
     * двух вэбэлементах
     */
    @Test
    void getPageRecoveryPasswordTest() {
        new AuthView(driver)
                .fillinputforgotpassword()
                .inputlogin(CORECTUSERNAME);
        assertThat(new ForgetPasswordView(driver).recoveryPassword, hasText(EXPRECOVERYPASSWORD));
        assertThat(new ForgetPasswordView(driver).recoveryPasswordHint, hasText(EXPRECOVERYPASSWORDHINT));
    }

    /**
     * 4 Тест кейс для проверки возможности перейти на страницу авторизации со страницы восстановления пароля
     * Для подтверждения корректных действий системы было выполнено сравнение на наличие ожидаемого текста в
     * в вэбэлементе на странице авторизации
     */
    @Test
    void getAuthPageFromRecoveryPasswordPage() {
        new AuthView(driver)
                .fillinputforgotpassword()
                .clickBackButton();
        assertThat(new AuthView(driver).locateAuthPage, hasText(EXPBACKTOAUTHPAGETEXTELEMENT));
    }

    /**
     * 5 Тест кейс для проверки возможности отправки ссылки для восстановления пароля системой
     * Для подтверждения корректных действий системы было выполнено сравнение на наличие появления
     * ожидаемого элемента с текстом (ссылка успешно отправлена)
     */
    @Test
    @Order(2)
    void sendRecoveryPasswordlink() {
        new AuthView(driver)
                .fillinputforgotpassword()
                .inputlogin(CORECTUSERNAME)
                .clickSubmitBitton();
        assertThat(new ForgetPasswordView(driver).notificationSuccess,
                hasText(EXPRECOVERYPASSWORDSUCCSESSNOTIFICATION));
    }

    /**
     * 6 Тест кейс для проверки отсутствия возможности отправки ссылки для восстановления пароля системой
     * при введении некорректных данных о пользователе
     * Для подтверждения корректных действий системы было выполнено сравнение на наличие появления
     * ожидаемого элемента с текстом (Пользователь с таким именем не найден)
     */
    @Test
    void senRecoveryPasswordLinkWithIncorrectData() {
        new AuthView(driver)
                .fillinputforgotpassword()
                .inputlogin(INCORRECTUSERNAME)
                .clickSubmitBittonWithIncorrectData();
        assertThat(new ForgetPasswordView(driver).unNotificationSuccess,
                hasText(XPATHUSERNOTFOUND));

    }

    /**
     * 7 Тест кейс для проверки получения ссылки для восстановления пароля системой
     * Для подтверждения корректных действий системы было выполнен вход в систему, переход в раздел уведомления
     * далее сообщения, тело сообщения с переходом по полученной ссылке с восстановлением пароля после выполнения
     * тест кейса №5 и сравнение по тексту элементов, что ссылка получена для конкретного пользователя с его данными.
     */
    @Test
    @Order(3)
    void GetLinkRecoveryPassword() {
        new AuthView(driver)
                .login(CORECTUSERNAME, CORRECTUSERPASSWORD)
                .clicknotificationbtn()
                .clickMessageWithLinkRecoveryPassword()
                .clickLinkRecoveryPassword();
        assertThat(new MessagePage(driver).currentUserData, hasText(CURRENTFULLUSERDATA));
        assertThat(new MessagePage(driver).changebutton, hasText(CHANGEPASSWORDBTN));
    }


}
