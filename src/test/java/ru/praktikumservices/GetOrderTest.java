package ru.praktikumservices;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.client.OrderClient;
import ru.praktikumservices.step.OrderSteps;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;


public class GetOrderTest {


    private OrderSteps orderSteps;


    @Before
    public void setUp() {
        orderSteps = new OrderSteps(new OrderClient());
    }


    @Test
    @DisplayName("получение списка заказов")
    public void getOrder() {
        orderSteps.getOrder()
                .statusCode(200)
                .body("orders", notNullValue());

    }
}
