package com.hibi.www.cusexception;

public class ImageCodeIllegalException extends IllegalArgumentException {
    public ImageCodeIllegalException() {
    }

    public ImageCodeIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageCodeIllegalException(Throwable cause) {
        super(cause);
    }

    public ImageCodeIllegalException(String message) {
        super(message);
    }
}
