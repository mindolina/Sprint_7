package ru.praktikumservices.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikumservices.dto.CourierCreateRequest;
import ru.praktikumservices.dto.CourierLoginRequest;

public class CourierClient extends RestClient {

    public Response create (CourierCreateRequest courierCreateRequest){
        return getDefaultRequestSpecification()
                .body(courierCreateRequest)
                .when()
                .post("/api/v1/courier");


    }

    public Response login (CourierLoginRequest courierLoginRequest){
                return getDefaultRequestSpecification()
                        .body(courierLoginRequest)
                        .when()
                        .post("/api/v1/courier/login");
    }

    public Response delete (int id){
        return getDefaultRequestSpecification()
                .delete("/api/v1/courier/"+id);
    }


}
