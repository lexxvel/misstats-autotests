package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import pojos.DeleteTaskPojoRq;
import system.Specification;

import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Task {
    private final RequestSpecification requestSpecification;
    ObjectMapper mapper = new ObjectMapper();

    public Task(String url) {
        Specification spec = new Specification();
        this.requestSpecification = spec.getRequestSpecification(url);
    }

    /**
     * Метод для удаления задачи по номеру задачи {{taskNumber}} по API.
     * @param taskNumber номер задачи
     * @return статус запроса
     */
    public int deleteTask(String taskNumber) {
        DeleteTaskPojoRq taskPojoRq = new DeleteTaskPojoRq(taskNumber);

        Map<String, String> map =
                mapper.convertValue(taskPojoRq, new TypeReference<Map<String, String>>() {
                });

        map.values().removeAll(Collections.singleton(null));

        try {
            Response response = given()
                    .spec(requestSpecification)
                    .body(map)
                    .post(Routes.deleteTask)
                    .then()
                    .log().all()
                    .extract()
                    .response();
            return response.getStatusCode();
        } catch (Throwable t) {
            t.printStackTrace();
            return 0;
        }
    }
}
