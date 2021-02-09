package com.dormitory.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 4334354002099527481L;
    private int code;
    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }
}
