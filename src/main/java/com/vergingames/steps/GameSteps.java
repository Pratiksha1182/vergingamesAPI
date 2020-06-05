package com.vergingames.steps;


import com.vergingames.constants.EndPoints;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GameSteps {

    @Step("Getting all games Information")
    public ValidatableResponse gettingAllGamesInfo() {
        return SerenityRest.rest().given()
                .log().all()
                .when()
                .get(EndPoints.GET_BINGO_LOBBY_FEED)
                .then().log().ifValidationFails()
                .parser("text/plain;charset=UTF-8", Parser.JSON);

    }
}
