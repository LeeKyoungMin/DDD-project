package com.ddd.membership.global.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Getter
@Setter
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Value<T> {

    private T value;

    @JsonIgnore
    public Value<T> responseOk(T value){
        return this.setValue(value);
    }

    @JsonIgnore
    public Value<T> serverError(T value){
        return this.setValue(value);
    }

}
