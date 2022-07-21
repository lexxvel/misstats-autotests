package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Класс для формирования запроса удаления задачи
 */
@Data
public class DeleteTaskPojoRq {
    public DeleteTaskPojoRq(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @JsonProperty("Task_Number")
    private String taskNumber;
}
