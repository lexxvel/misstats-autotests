package system;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    /**
     * Настройки спецификации запроса.
     */
    public RequestSpecification getRequestSpecification(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Настройки спецификации ответа.
     */
    public ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                //.log(LogDetail.STATUS)
                .log(LogDetail.BODY).expectStatusCode(200)
                .build();
    }
}
