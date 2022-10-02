package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionController;
import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// ControllerAdvice 는 대상을 지정하지 않으면 모든 컨트롤러에 적용된다. (글로벌 적용)

// 대상 컨트롤러 지정 방법
//@RestControllerAdvice(annotations = RestController.class) // RestController 클래스에 적용
//@RestControllerAdvice(basePackages = "package hello.exception.api")    // 패키지 하위의 컨트롤러에 모두 적용    //보통 이 방법 사용
//@RestControllerAdvice(assignableTypes = {ApiExceptionController.class, ApiExceptionV2Controller.class}) // 특정 컨트롤러 클래스 지정
@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("exceptionHandler ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

}
