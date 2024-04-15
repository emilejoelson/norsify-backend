package ma.norsysafr.norsifyApi.exception;


import java.util.Date;

public record ErrorDetail(
        Date timestamp,
        int status,
        String error,
        String message,
        String details
) {
}