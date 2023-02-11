package com.ddd.membership.domain.point.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.category.domain.Category;
import com.ddd.membership.domain.point.dto.PointDto;
import com.ddd.membership.domain.user.domain.User;
import com.ddd.membership.global.exception.MemberShipRuntimeException;
import com.ddd.membership.global.response.StatusEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Point {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private long point;

    @Enumerated(EnumType.STRING)
    private Category state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public long plusPoint(long point){
        this.point += point;
        return this.point;
    }

    public long minusPoint(long point){
        long amount = this.point - point;
        if(amount < 0){
            throw new MemberShipRuntimeException(StatusEnum.EXCEED_USE_POINT);
        }
        return amount;
    }

    @Builder
    public Point(String id, long point, Category state){
        this.id = id;
        this.point = point;
        this.state = state;
    }

    public Point(){}

    public PointDto convertEntityToDto() {
        PointDto savePointDto = new PointDto();
        BeanUtils.copyProperties(this, savePointDto);
        return savePointDto;
    }
}
