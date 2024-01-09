import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.client.CourierClient;
import ru.praktikumservices.step.CourierSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginTest {
    private CourierSteps courierSteps;
    String login = "Sanchez";
    String password = "qwerty";
    String firstName = "Rick";


    @Before
    public void setUp() {

        courierSteps = new CourierSteps(new CourierClient());

    }

    @Test
    @DisplayName("успешный логин курьера в системе")
    public void courierLogin() {
        courierSteps
                .create(login, password, firstName);
        courierSteps
                .login(login, password)
                .statusCode(200)
                .body("id", notNullValue());
    }


    @Test
    @DisplayName("некорректно указан логин курьера")
    public void courierLoginWithErrorLogin() {
        courierSteps
                .create(login, password, firstName);
        courierSteps
                .login("Sanchezzz", password)
                .statusCode(404);
    }

    @Test
    @DisplayName("некорректно указан пароль курьера")
    public void courierLoginWithErrorPassword() {
        courierSteps
                .create(login, password, firstName);
        courierSteps
                .login(login, "qwertyy")
                .statusCode(404);
    }

    @Test
    @DisplayName("запрос без логина")
    public void courierLoginWithOutLogin() {
        courierSteps
                .create(login, password, firstName);

        String login = "";
        courierSteps
                .login(login, password)
                .log().all()
                .statusCode(400);
    }

    @Test
    @DisplayName("запрос без пароля")
    public void courierLoginWithOutPassword() {
        courierSteps
                .create(login, password, firstName);


        String password = "";
        courierSteps
                .login(login, password)
                .statusCode(400);
    }


    @After
    public void deleteall() {
        courierSteps.deleteTestCourier(login, password);
    }
}
