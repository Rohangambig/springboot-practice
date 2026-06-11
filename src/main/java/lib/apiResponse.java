package lib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class apiResponse {
    private String message;
    private HttpStatus status;
    private Object data;

    public ResponseEntity<apiResponse> handleResponse(String message, HttpStatus status, Object data) {
        apiResponse response = new apiResponse(message, status, data);
        return new ResponseEntity<>(response, status    );
    }
}
