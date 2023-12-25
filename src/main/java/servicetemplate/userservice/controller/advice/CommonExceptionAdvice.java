package servicetemplate.userservice.controller.advice;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import servicetemplate.userservice.data.model.ExceptionModel;
import servicetemplate.userservice.exception.AlreadyExistsException;
import servicetemplate.userservice.exception.NotFoundException;

@RestControllerAdvice
public class CommonExceptionAdvice {

  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ExceptionModel> handleAccessDeniedException(
      AccessDeniedException exception) {
    return ResponseEntity.status(UNAUTHORIZED)
        .body(new ExceptionModel(exception.getMessage(), UNAUTHORIZED.toString()));
  }

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionModel> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(NOT_FOUND)
        .body(new ExceptionModel(exception.getMessage(), NOT_FOUND.toString()));
  }

  @ResponseStatus(CONFLICT)
  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ExceptionModel> handleAlreadyExistsException(
      AlreadyExistsException exception) {
    return ResponseEntity.status(CONFLICT)
        .body(new ExceptionModel(exception.getMessage(), CONFLICT.toString()));
  }
}
