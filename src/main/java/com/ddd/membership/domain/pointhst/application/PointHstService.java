package com.ddd.membership.domain.pointhst.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ddd.membership.domain.pointhst.dao.PointHstRepository;
import com.ddd.membership.domain.pointhst.dto.PointHstDto;
import com.ddd.membership.domain.user.dao.UserRepository;
import com.ddd.membership.global.exception.MemberShipRuntimeException;
import com.ddd.membership.global.response.StatusEnum;

@Service
public class PointHstService {

    private UserRepository userRepository;
    private PointHstRepository pointHstRepository;

    public PointHstService(UserRepository userRepository, PointHstRepository pointHstRepository){
        this.userRepository = userRepository;
        this.pointHstRepository = pointHstRepository;
    }
    
    public List<PointHstDto> getHsts(String barcodeNo, LocalDateTime startTm, LocalDateTime endTm) {
        
        getUser(barcodeNo);
        
        var pointHst = pointHstRepository.findByBarcodeNoAndApprovedAtBetween(barcodeNo, startTm, endTm);

        List<PointHstDto> pointHstDto = new ArrayList<>();
        pointHstDto = pointHst.stream()
                              .map(e -> e.convertEntityToDto())
                              .collect(Collectors.toList());

        return pointHstDto;
    }

    private void getUser(String barcodeNo){
         userRepository.findByBarcodeNo(barcodeNo)
                             .orElseThrow(() -> new MemberShipRuntimeException(StatusEnum.NOT_REGISTER_BARCODE));
    }

}
