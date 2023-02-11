package com.ddd.membership.domain.pointhst.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.category.domain.Category;
import com.ddd.membership.domain.pointhst.dto.PointHstDto;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class PointHst {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String barcodeNo;

    private LocalDateTime approvedAt;

    private String partnerName;

    private String gubun;

    @Enumerated(EnumType.STRING)
    private Category state;

    @Builder
    public PointHst(String id, String barcodeNo, LocalDateTime approvedAt, String partnerName, String gubun, Category state){
        this.id = id;
        this.barcodeNo = barcodeNo;
        this.approvedAt = approvedAt;
        this.partnerName = partnerName;
        this.gubun = gubun;
        this.state = state;
    }

    public PointHst(){}

    public PointHstDto convertEntityToDto(){
        PointHstDto pointHstDto = new PointHstDto();
        BeanUtils.copyProperties(this, pointHstDto);
        return pointHstDto;
    }
}
