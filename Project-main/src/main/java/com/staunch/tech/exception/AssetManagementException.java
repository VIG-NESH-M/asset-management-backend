package com.staunch.tech.exception;

import lombok.experimental.StandardException;

@StandardException
public class AssetManagementException extends RuntimeException {


    public AssetManagementException() {
        super();
    }

    /**
     *
     * @param message
     */
    public AssetManagementException(String message) {
        super(message);
    }

}
