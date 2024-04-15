package ma.norsysafr.norsifyApi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ErrorDetail> createErrorResponse(HttpStatus status, Exception e, WebRequest webRequest) {
        ErrorDetail errorDetails = new ErrorDetail(
                new Date(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFoundExceptionHandler(RessourceNotFoundException e, WebRequest request) {
        return createErrorResponse(HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetail> handleConflict(DataIntegrityViolationException e, WebRequest request) {
        return createErrorResponse(HttpStatus.CONFLICT, e, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> accessDeniedExceptionHandler(AccessDeniedException e, WebRequest webRequest) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, e, webRequest);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetail> methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException e, WebRequest webRequest) {
        return createErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, e, webRequest);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDetail> invalidPasswordExceptionHandler(InvalidPasswordException e, WebRequest request) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e, request);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetail> blogApiExceptionHandler(AppException e, WebRequest request) {
        return createErrorResponse(e.getStatus(), e, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> GlobalExceptionHandler(Exception e, WebRequest request) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }
}
