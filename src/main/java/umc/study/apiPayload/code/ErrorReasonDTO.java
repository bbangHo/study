package umc.study.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {

    private final boolean isSuccess;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public boolean getIsSuccess(){
        return isSuccess;
    }
}
