package com.ddd.membership.global.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class Response<T> {

    @JsonProperty("common")
    private Common common;

    @JsonProperty("value")
    private T value;

    @JsonIgnore
    public Response<T> responseOk() {
        return (Response<T>) Response.builder()
                .common(Common.builder().build().responseOk())
                .build();
    }

    @JsonIgnore
    public Response<T> responseOk(T value) {
        return (Response<T>) Response.builder()
                .value(Value.builder().build().responseOk(value))
                .common(Common.builder().build().responseOk())
                .build();
    }

    @JsonIgnore
    public Response<T> serverError(StatusEnum status) {
        return (Response<T>) Response.builder()
                .common(Common.builder().build().serverError(status))
                .build();
    }
}
