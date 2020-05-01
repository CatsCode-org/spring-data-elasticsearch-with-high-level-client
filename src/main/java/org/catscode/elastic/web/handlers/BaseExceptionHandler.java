package org.catscode.elastic.web.handlers;

import org.catscode.elastic.exceptions.EntityCreationException;
import org.catscode.elastic.exceptions.EntityNotFoundException;
import org.catscode.elastic.exceptions.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(EntityCreationException.class)
    public ResponseEntity<Void> handleEntityCreationException(Exception ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler({ElasticsearchException.class})
    public ResponseEntity<ErrorResponse> handleElasticSearchErrorResponse(Exception ex) {
        final ErrorResponse msg = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getCause().toString());
        log.error("An exception occurred during communication with elasticsearch", ex);
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex) {
        final ErrorResponse msg = new ErrorResponse(HttpStatus.NO_CONTENT.toString(), ex.getMessage());
        log.info(ex.getMessage());
        return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
    }
}
