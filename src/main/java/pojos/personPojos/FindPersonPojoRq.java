package pojos.personPojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindPersonPojoRq {
    public FindPersonPojoRq(String personFullName) {
        this.personFullnamw = personFullName;
    }

    @JsonProperty("Person_Fullname")
    private String personFullnamw;
}
