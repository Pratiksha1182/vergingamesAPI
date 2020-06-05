package com.vergingames.bingolobby;


import com.vergingames.steps.GameSteps;
import com.vergingames.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SerenityRunner.class)
public class BingoTest extends TestBase {
    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;

    @Steps
    GameSteps gameSteps;


    @Title("verifyThatGamesExtractedHasDefaultGameFrequencyIs30000")
    @Test
    public void test001() {
        ValidatableResponse response = gameSteps.gettingAllGamesInfo().statusCode(200);
        defaultGameFrequency = response.extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        System.out.println("The Default Game Frequency values in the list are : " + defaultGameFrequency);


    }


    @Title("This test will verify that the startTime for all Games is always future timestamp")
    @Test
    public void test002() {
        ValidatableResponse response = gameSteps.gettingAllGamesInfo().statusCode(200);
        timeStamp = response.extract().response().body().path("timestamp").toString();
        startTime = response.extract().response().body().path("bingoLobbyInfoResource.streams.startTime").toString();
        System.out.println("-------------------StartingTest--------------------");
        System.out.println("The TimeStamp is : " + timeStamp);
        System.out.println("The Start Time is : " + startTime);
        System.out.println("-------------------End of test----------------------");
        assertThat(startTime,greaterThan(timeStamp));
    }

}
