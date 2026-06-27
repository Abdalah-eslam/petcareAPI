package org.petcare.petcare.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResponseAPI {
    private String message;
    private String status;
    private Object data;
}
