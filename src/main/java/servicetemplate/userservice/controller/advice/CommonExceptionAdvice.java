package servicetemplate.userservice.controller.advice;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import servicetemplate.userservice.data.model.ExceptionModel;

@RestControllerAdvice
public class CommonExceptionAdvice {

  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ExceptionModel> handleAccessDeniedException(
      AccessDeniedException exception) {
    return ResponseEntity.status(UNAUTHORIZED)
        .body(new ExceptionModel(exception.getMessage(), UNAUTHORIZED.toString()));
  }
}
