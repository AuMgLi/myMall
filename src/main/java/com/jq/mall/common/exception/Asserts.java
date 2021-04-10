package com.jq.mall.common.exception;

import com.jq.mall.common.IErrorCode;

public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}
