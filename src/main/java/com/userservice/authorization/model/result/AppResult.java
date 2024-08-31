package com.userservice.authorization.model.result;

import com.userservice.authorization.common.constant.CommonErrorCode;
import com.userservice.authorization.model.dto.ErrorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppResult {
    private Integer code;
    private String message;
    private Object payload;

    public static ResponseEntity<AppResult> success() {
        return success("");
    }

    public static ResponseEntity<AppResult> success(final String msg) {
        return success(msg, null);
    }

    public static ResponseEntity<AppResult> success(final Object data) {
        return success("", data);
    }


    public static ResponseEntity<AppResult> success(final String msg, final Object data) {
        AppResult appResult = get(CommonErrorCode.SUCCESSFUL, msg, data);
        return new ResponseEntity<>(appResult, HttpStatus.OK);
    }

    public static ResponseEntity<AppResult> error(final Object data) {
        return error(null, data);
    }

    public static ResponseEntity<AppResult> error(final String msg, final Object data) {
        AppResult appResult =  get(CommonErrorCode.ERROR, msg, data);
        return new ResponseEntity<>(appResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static AppResult get(final int code, final String msg, final Object data) {
        return new AppResult(code, msg, data);
    }
}
