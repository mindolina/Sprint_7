package ru.praktikumservices.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikumservices.client.CourierClient;
import ru.praktikumservices.client.OrderClient;
import ru.praktikumservices.dto.CourierCreateRequest;
import ru.praktikumservices.dto.CourierLoginRequest;
import ru.praktikumservices.dto.CreateOrderRequest;

public class CourierSteps {
    private final CourierClient courierClient;


    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;

    }

    @Step("Send POST request to api/v1/courier")
    public ValidatableResponse create(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.create(courierCreateRequest).then();

    }

    @Step("Send POST request to api/v1/courier/login")
    public ValidatableResponse login(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.login(courierLoginRequest).then();

    }

    @Step("удаление тестовых данных")
    public void deleteTestCourier(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        int id = courierClient.login(courierLoginRequest).then().extract().body().path("id");
        courierClient.delete(id);
    }


}
