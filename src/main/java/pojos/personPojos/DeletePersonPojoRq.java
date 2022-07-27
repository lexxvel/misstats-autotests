package pojos.personPojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeletePersonPojoRq {
    public DeletePersonPojoRq(String personId) {
        this.personId = personId;
    }

    @JsonProperty("Person_id")
    private String personId;
}
