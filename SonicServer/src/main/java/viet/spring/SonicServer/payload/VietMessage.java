package viet.spring.SonicServer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class VietMessage {
    private int statusCode;
    private String message;
}
