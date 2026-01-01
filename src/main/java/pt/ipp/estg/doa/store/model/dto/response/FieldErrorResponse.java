package pt.ipp.estg.doa.store.model.dto.response;

public record FieldErrorResponse(
        String field,
        Object rejectedValue,
        String message
) {}