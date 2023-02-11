package com.ddd.membership.global.response;

import lombok.Getter;

@Getter
public enum StatusEnum {

    //기본 응답코드
    SUCCESS(200, "Success")
    , INVALID_REQUEST(400, "Bad Request")
    , DATA_NOT_FOUND(404, "Data Not Found")
    
    // 상점 에러 600대
    , NOT_REGISTER_PARTNER(600, "Not Register Partner")
    , DO_NOT_CHANGE_CATEGORY(601, "Do Not Change Category")

    // 유저 에러
    , USER_NOT_FOND(700, "User Not Found")
    , NOT_REGISTER_BARCODE(701, "Not Register Barcode")


    // 포인트 에러
    , EXCEED_USE_POINT(800, "Exceed usePoint")

    ;

    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
