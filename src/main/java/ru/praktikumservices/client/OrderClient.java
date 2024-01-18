package ru.praktikumservices.client;

import io.restassured.response.Response;
import ru.praktikumservices.dto.CourierCreateRequest;
import ru.praktikumservices.dto.CreateOrderRequest;

public class OrderClient extends RestClient {
    public Response createdOrder (CreateOrderRequest createOrderRequest) {
        return getDefaultRequestSpecification()
                .body(createOrderRequest)
                .when()
                .post("/api/v1/orders");
    }

    public Response getOrders (){
        return getDefaultRequestSpecification()
                .when()
                .get("/api/v1/orders");
    }
}
