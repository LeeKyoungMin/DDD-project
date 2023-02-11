package com.ddd.membership.domain.point.dto;

import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.point.domain.Point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PointDto {
    
    String partnerId;

    String barcodeNo;

    long point;

    @Builder
    public PointDto(String partnerId, String barcodeNo, long point){
        this.partnerId = partnerId;
        this.barcodeNo = barcodeNo;
        this.point = point;
    }
    
    public PointDto(){}

    public Point convertDtoToEntity() {
        Point point = new Point();
        BeanUtils.copyProperties(this, point);
        return point;
    }
}
