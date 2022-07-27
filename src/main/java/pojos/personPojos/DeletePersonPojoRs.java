package pojos.personPojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeletePersonPojoRs {
    @JsonProperty("status")
    public String status;

    @JsonProperty("message")
    public String message;
}
