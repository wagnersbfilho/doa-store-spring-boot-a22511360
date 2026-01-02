package pt.ipp.estg.doa.store.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pt.ipp.estg.doa.store.model.dto.response.ErrorResponse;
import pt.ipp.estg.doa.store.model.dto.response.FieldErrorResponse;
import pt.ipp.estg.doa.store.model.dto.response.ValidationErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handle 404
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        log.warn("Resource not found: path={}, message={}",
                request.getRequestURI(),
                ex.getMessage());

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Handle 409
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({
            InvalidOperationException.class,
            OutOfStockException.class,
            InvalidStatusTransitionException.class,
            DuplicateNifException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(
            RuntimeException ex,
            HttpServletRequest request) {

        log.warn("Business conflict: path={}, exception={}, message={}",
                request.getRequestURI(),
                ex.getClass().getSimpleName(),
                ex.getMessage());

        return buildErrorResponse(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    /**
     * Handle 400
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        log.warn("Validation failed: errors={}",
                ex.getBindingResult().getFieldErrors());

        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()))
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Validation failed",
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handle 500
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error: path={}",
                request.getRequestURI(),
                ex);

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error occurred",
                request.getRequestURI()
        );
    }

    /**
     * Error message builder
     *
     * @param status
     * @param message
     * @param path
     * @return
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String message,
            String path) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );

        return ResponseEntity.status(status).body(response);
    }
}
