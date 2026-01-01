package pt.ipp.estg.doa.store.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<FieldErrorResponse> errors
) {}
