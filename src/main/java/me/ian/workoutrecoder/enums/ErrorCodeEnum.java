package me.ian.workoutrecoder.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    SYSTEM_ERROR(10000, "System error"),
    PARAMETER_WRONG(10001, "Parameter wrong"),
    OPERATION_FAILED(10002, "Operation failed"),
    ACCOUNT_ALREADY_EXIST(10003, "Account is already exist"),
    ACTION_ALREADY_EXIST(10004, "Action is already exist"),
    DATA_NOT_EXIST(11000, "Data not exist")
    ;


    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (code == errorCodeEnum.getCode()) {
                return errorCodeEnum.getMsg();
            }
        }
        return null;
    }
}
