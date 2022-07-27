package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.personPojos.DeletePersonPojoRq;
import pojos.personPojos.DeletePersonPojoRs;
import pojos.personPojos.FindPersonPojoRq;
import pojos.personPojos.FindPersonPojoRs;
import system.MisstatsSettings;
import system.Specification;

import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Person {
    private final RequestSpecification requestSpecification;
    ObjectMapper mapper = new ObjectMapper();

    public Person(String url) {
        Specification spec = new Specification();
        this.requestSpecification = spec.getRequestSpecification(url);
    }

    public String findPersonIdByFullname(String personFullname) {
        FindPersonPojoRq findPersonPojoRq = new FindPersonPojoRq(personFullname);

        Map<String, String> map =
                mapper.convertValue(findPersonPojoRq, new TypeReference<Map<String, String>>() {
                });
        map.values().removeAll(Collections.singleton(null));

        try {
            Response response = given()
                    .spec(requestSpecification)
                    .body(map)
                    .post(Routes.findPerson)
                    .then()
                    .log().all()
                    .extract()
                    .response();
            System.out.println("Ответ сервера: " + response.getBody().asString());
            System.out.println("JSON: " + response.getBody().asString());
            Gson g = new Gson();
            //FindPersonPojoRs findPersonPojoRs = g.fromJson(response.getBody().asString(), FindPersonPojoRs.class);
            FindPersonPojoRs findPersonPojoRs = response.getBody().jsonPath().getObject("", FindPersonPojoRs.class);
            System.out.println("PersonId: " + findPersonPojoRs.personId);
            if (findPersonPojoRs.personId == null) {
                if (findPersonPojoRs.status.equals("false")) {
                    System.out.println("Удаление не удалось по причине: " + findPersonPojoRs.message);
                    return null;
                } else {
                    throw new AssertionError("Ошибка исполнения метода поиска сотрудника");
                }
            } else {
                return findPersonPojoRs.personId;
            }
        } catch (Throwable t) {
            MisstatsSettings.fail(t);
            return null;
        }
    }

    public int deletePersonById(String personId) {
        DeletePersonPojoRq deletePersonPojoRq = new DeletePersonPojoRq(personId);

        Map<String, String> map =
                mapper.convertValue(deletePersonPojoRq, new TypeReference<Map<String, String>>() {
                });
        map.values().removeAll(Collections.singleton(null));

        try {
            Response response = given()
                    .spec(requestSpecification)
                    .body(map)
                    .post(Routes.deletePerson)
                    .then()
                    .log().all()
                    .extract()
                    .response();
            System.out.println("Ответ сервера: " + response.getBody().asString());
            Gson g = new Gson();
            //FindPersonPojoRs findPersonPojoRs = g.fromJson(response.getBody().asString(), FindPersonPojoRs.class);
            DeletePersonPojoRs deletePersonPojoRs = response.getBody().jsonPath().getObject("", DeletePersonPojoRs.class);
            System.out.println("Статус: " + deletePersonPojoRs.status);
            if (deletePersonPojoRs.status.equals("false")) {
                System.out.println("Удаление не удалось по причине: " + deletePersonPojoRs.message);
                return response.getStatusCode();
            } else if (deletePersonPojoRs.status.equals("true")) {
                return response.getStatusCode();
            } else {
                throw new AssertionError("При удалении сотрудника произошла ошибка");
            }
        } catch (Throwable t) {
            MisstatsSettings.fail(t);
            return 0;
        }
    }
}
