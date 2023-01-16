package host.ankh.jeyseylearn.infrastructure.jaxrs;

import lombok.Data;

/**
 * 返回体结构，具体包括三项： code  message  data
 */
@Data
public class CodedMessage {
    private Integer code;
    private String message;
    private Object data;

    public CodedMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}