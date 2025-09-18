package br.com.mrb.application.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request) {
        Map<String, List<String>> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList())));
        var response = buildError(BAD_REQUEST, exception.getMessage(), errors);
        return ResponseEntity.status(BAD_REQUEST).body(response);
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            WebRequest request) {
        var response = buildError(BAD_REQUEST, exception.getMessage(), null);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception,
            WebRequest request) {
        var response = buildError(METHOD_NOT_ALLOWED, exception.getMessage(), null);
        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException exception,
            WebRequest request) {
        String unsupported = (exception.getContentType() != null)
                ? exception.getContentType().toString()
                : "unknown";
        String supported = exception.getSupportedMediaTypes().stream()
                .map(MediaType::toString)
                .collect(Collectors.joining(", "));
        String message = String.format("Content type '%s' is not supported. Supported types: %s",
                unsupported, supported);
        var response = buildError(UNSUPPORTED_MEDIA_TYPE, message, null);
        return ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE).body(response);    }

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

    @ExceptionHandler(PostNotFoundException.class)
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
