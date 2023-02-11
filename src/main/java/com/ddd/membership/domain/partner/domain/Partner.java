package com.ddd.membership.domain.partner.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.ddd.membership.domain.category.domain.Category;
import com.ddd.membership.global.exception.MemberShipRuntimeException;
import com.ddd.membership.global.response.StatusEnum;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Partner {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String partnerName;

    @Enumerated(EnumType.STRING)
    private Category state;

    public void setState(Category state){
        if(this.getState() != null){
            throw new MemberShipRuntimeException(StatusEnum.DO_NOT_CHANGE_CATEGORY);
        }
        this.state = state;
    }

    @Builder
    public Partner(String id, String partnerName, Category state){
        this.id = id;
        this.partnerName = partnerName;
        this.state = state;
    }

    public Partner(){}
}
