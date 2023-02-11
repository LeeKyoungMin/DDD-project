package com.ddd.membership.domain.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;

import com.ddd.membership.domain.point.domain.Point;
import com.ddd.membership.domain.user.dto.UserDto;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 9)
    private Long id;

    private String name;

    private String pwd;

    @Column(name = "BARCODE_NO", length = 10)
    private String barcodeNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Point> point = new ArrayList<>();

    public String issueBardcode(){
        this.barcodeNo = RandomStringUtils.random(10, false, true);
        return this.barcodeNo;
    }

    public UserDto convertEntityToDto(){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this, userDto);

        if(null != this.getPoint() && this.getPoint().size() != 0){
            userDto.setPoint(this.getPoint().stream().map(Point::convertEntityToDto).collect(Collectors.toList()));
        }

        return userDto;
    }

    public User(){}

    @Builder
    public User(Long id, String name, String pwd, String barcodeNo, List<Point> point){
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.barcodeNo = barcodeNo;
        this.point = point;
    }

    public void add(Point point){
        point.setUser(this);
        this.point.add(point);
    }
}
