package ru.praktikumservices;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.client.CourierClient;
import ru.praktikumservices.step.CourierSteps;
import static org.hamcrest.core.Is.is;

public class CreateCourierTest {
    private CourierSteps courierSteps;
    String login = "Smmith";
    String password = "qwerty";
    String firstName = "Мorty";

    @Before
    public void setUp() {

        courierSteps = new CourierSteps(new CourierClient());
    }

    @Test
    @DisplayName("создание курьера")
    public void createCourier() {
        courierSteps
                .create(login, password, firstName)
                .statusCode(201)
                .body("ok", is(true));
    }


    @Test
    @DisplayName("нельзя создать двух одинаковых курьеров")
    public void createCourierDublicate() {
        courierSteps
                .create(login, password, firstName);
        courierSteps
                .create(login, password, firstName)
                .statusCode(409);
    }

    @Test
    @DisplayName("Cоздать курьера без имени")
    public void createCourierWithoutFirstName() {

        String firstName = "";
        courierSteps
                .create(login, password, firstName)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void createCourierWithoutLogin() {
        courierSteps
                .create(login, password, firstName);

        String login = "";
        courierSteps
                .create(login, password, firstName)
                .statusCode(400);
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void createCourierWithoutPassword() {
        courierSteps
                .create(login, password, firstName);

        String password = "";
        courierSteps
                .create(login, password, firstName)
                .statusCode(400);
    }

    @After
    public void deleteall() {
        courierSteps.deleteTestCourier(login, password);
    }

}
