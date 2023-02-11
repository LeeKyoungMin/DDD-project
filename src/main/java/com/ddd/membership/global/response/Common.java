package com.ddd.membership.global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Builder
@Accessors(chain = true)
public class Common {
    
    private int code;

    private String message;

    public Common responseOk(){
        return this.setCode(StatusEnum.SUCCESS.getCode())
                    .setMessage(StatusEnum.SUCCESS.getMessage());
    }

    public Common serverError(StatusEnum statusEnum){
        return this.setCode(statusEnum.getCode())
                    .setMessage(statusEnum.getMessage());
    }
}
