package com.ddd.membership.domain.pointhst.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.category.domain.Category;
import com.ddd.membership.domain.pointhst.domain.PointHst;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PointHstDto {
    
    private String id;

    private String barcodeNo;

    private LocalDateTime approvedAt;

    private String partnerName;

    private String gubun;

    private Category state;

    public PointHst convertDtoToEntity(){
        PointHst pointHst = new PointHst();
        if(this != null){
            BeanUtils.copyProperties(this, pointHst);
        }
        return pointHst;
    }
}
