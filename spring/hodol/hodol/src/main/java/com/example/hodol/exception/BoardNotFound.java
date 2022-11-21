package com.example.hodol.exception;

/**
 * status -> 404
 */
public class BoardNotFound extends JkException{
    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public BoardNotFound() {
        super(MESSAGE);
    }

    public BoardNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
