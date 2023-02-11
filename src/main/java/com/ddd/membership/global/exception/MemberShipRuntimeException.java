package com.ddd.membership.global.exception;

import com.ddd.membership.global.response.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MemberShipRuntimeException extends RuntimeException{

    private StatusEnum statusEnum;
}
