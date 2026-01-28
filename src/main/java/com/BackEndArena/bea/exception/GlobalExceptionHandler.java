package com.BackEndArena.bea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//
//
//    /* =========================
//   404 – Resource Not Found
//   ========================= */
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFound(
//            ResourceNotFoundException ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                ex.getMessage(),
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(error);
//    }
//
//    /* =========================
//     400 – Business Rule Failure
//     ========================= */
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalState(
//            IllegalStateException ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                ex.getMessage(),
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(error);
//    }
//
//
//    /* =========================
//      500 – Unexpected Errors
//      ========================= */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(
//            Exception ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Something went wrong",
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(error);
//    }
//
//    /* =========================
//     401 – Unauthorized
//     ========================= */
//    @ExceptionHandler(UnauthorisedException.class)
//    public ResponseEntity<ErrorResponse> handleUnauthorized(
//            UnauthorisedException ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.UNAUTHORIZED.value(),
//                ex.getMessage(),
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(error);
//    }
//
//    /* =========================
//      403 – Forbidden
//      ========================= */
//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity<ErrorResponse> handleForbidden(
//            ForbiddenException ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.FORBIDDEN.value(),
//                ex.getMessage(),
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body(error);
//    }
//
//     /* =========================
//     409 – Duplicate Attempt
//     ========================= */
//
//    @ExceptionHandler(AttemptAlreadyExsistsException.class)
//    public ResponseEntity<ErrorResponse> handleAttemptAlreadyExists(
//            AttemptAlreadyExsistsException ex
//    ) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.CONFLICT.value(),
//                ex.getMessage(),
//                Instant.now()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(error);
//    }







//}
