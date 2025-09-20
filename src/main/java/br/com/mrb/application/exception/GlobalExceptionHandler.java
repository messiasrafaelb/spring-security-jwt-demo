package br.com.mrb.application.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorResponse buildError(HttpStatus status, String message, Map<String, List<String>> fieldErrors) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .fieldErrors(fieldErrors)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException exception,
            WebRequest request) {
        Map<String, List<String>> errors = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.groupingBy(violation -> violation.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage,
                                Collectors.toList())));
        var response = buildError(BAD_REQUEST, exception.getMessage(), errors);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException exception,
            WebRequest request) {
        var response = buildError(NOT_FOUND, exception.getMessage(), null);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            WebRequest request) {
        var response = buildError(CONFLICT, exception.getMessage(), null);
        return ResponseEntity.status(CONFLICT).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException exception,
            WebRequest request) {
        var response = buildError(FORBIDDEN, exception.getMessage(), null);
        return ResponseEntity.status(FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception exception,
            WebRequest request) {
        var response = buildError(INTERNAL_SERVER_ERROR, exception.getMessage(), null);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            Exception exception,
            WebRequest request) {
        var response = buildError(BAD_REQUEST, exception.getMessage(), null);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(
            Exception exception,
            WebRequest request) {
        var response = buildError(NOT_FOUND, exception.getMessage(), null);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(
            Exception exception,
            WebRequest request) {
        var response = buildError(NOT_FOUND, exception.getMessage(), null);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthorNotFoundException(
            Exception exception,
            WebRequest request) {
        var response = buildError(NOT_FOUND, exception.getMessage(), null);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            Exception exception,
            WebRequest request) {
        var response = buildError(NOT_FOUND, exception.getMessage(), null);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }
}
