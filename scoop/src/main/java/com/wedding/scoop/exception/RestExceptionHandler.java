package com.wedding.scoop.exception;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.exception.badrequest.InvalidOAuthProviderException;
import com.wedding.scoop.exception.notfound.MemberNotFoundException;
import com.wedding.scoop.exception.unauthorized.JwtExpiredException;
import com.wedding.scoop.exception.unauthorized.JwtNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * INTERNAL_SERVER_ERROR 처리 메소드
     * @param ex
     * @param model
     * @return ApiResponse<ErrorResponseForm>
     */
    @ExceptionHandler(
            RuntimeException.class
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorResponseForm> runtimeExceptionHandler(Exception ex, Model model) {

        return ApiResponse.fail(500,
                new ApiResponse.Body<>(
                        ErrorResponseForm.builder()
                                .title(ex.getMessage())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .timestamp(ZonedDateTime.now().toString())
                                .build()),
                "Internal Fail"
        );
    }
    @ExceptionHandler({
            MemberNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponseForm> notFoundHandler(Exception ex, Model model) {
        return ApiResponse.notFoundFail(
                new ApiResponse.Body<>(ErrorResponseForm.builder()
                        .title(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(ZonedDateTime.now().toString())
                        .build()),
                "Not found error"
        );
    }
    @ExceptionHandler({
            JwtExpiredException.class,
            JwtNotValidException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<ErrorResponseForm> unauthorizedHandler(Exception ex, Model model) {
        return  ApiResponse.notFoundFail(
                new ApiResponse.Body<>(ErrorResponseForm.builder()
                        .title(ex.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .timestamp(ZonedDateTime.now().toString())
                        .build()),
                "Unauthorized error"
        );
    }

//    @ExceptionHandler({
//    })
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ApiResponse<ErrorResponseForm> forbiddenHandler(Exception ex, Model model) {
//        return ApiResponse.forbiddenFail(new ApiResponse.Body<>(ErrorResponseForm.builder()
//                .title(ex.getMessage())
//                .status(HttpStatus.FORBIDDEN.value())
//                .timestamp(ZonedDateTime.now().toString())
//                .build()),
//                "Forbidden error"
//                );
//    }

    @ExceptionHandler({
            InvalidOAuthProviderException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponseForm> badReqeustHandlder(Exception ex, Model model) {
        return ApiResponse.badRequestFail(new ApiResponse.Body<>(ErrorResponseForm.builder()
                        .title(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(ZonedDateTime.now().toString())
                        .build()),
                "bad request error"
        );
    }



}
