package user;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserClient{

    public static String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth/";
    private final String REGISTER = "register";
    private final String LOGIN = "login";
    private final String USER = "user";
    private String userToken;

    protected RequestSpecification getSpec() {
        return given().log().all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URL);
    }

    protected RequestSpecification getSpecWithAuth(String userToken) {
        return given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", userToken)
                .baseUri(BASE_URL);
    }

    public ValidatableResponse create(User user) {
        return getSpec()
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    public ValidatableResponse login(UserCredentials creds) {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public String getToken(UserCredentials creds){
        return login(creds)
                .extract()
                .path("accessToken");
    }

    public void delete(String userToken) {
        getSpecWithAuth(userToken)
                .when()
                .delete(USER)
                .then().log().all();
    }

    public void tearDown(UserCredentials creds) {
        userToken = getToken(creds);
        delete(userToken);
    }
}
