package com.ddd.membership.domain.user.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.point.dto.PointDto;
import com.ddd.membership.domain.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;

    private String name;

    private String pwd;

    private String barcodeNo;

    private List<PointDto> point;

    public User convertDtoToEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public UserDto(){}

    @Builder
    public UserDto(Long id, String name, String pwd, String barcodeNo, List<PointDto> point){
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.barcodeNo = barcodeNo;
        this.point = point;
    }

    public void setPoint(List<PointDto> point){
        this.point = point;
    }
}
