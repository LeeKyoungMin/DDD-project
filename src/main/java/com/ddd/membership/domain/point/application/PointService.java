package com.ddd.membership.domain.point.application;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddd.membership.domain.partner.dao.PartnerRepository;
import com.ddd.membership.domain.partner.domain.Partner;
import com.ddd.membership.domain.point.domain.Point;
import com.ddd.membership.domain.point.dto.PointDto;
import com.ddd.membership.domain.pointhst.dao.PointHstRepository;
import com.ddd.membership.domain.pointhst.domain.PointHst;
import com.ddd.membership.domain.user.dao.UserRepository;
import com.ddd.membership.domain.user.domain.User;
import com.ddd.membership.global.exception.MemberShipRuntimeException;
import com.ddd.membership.global.response.StatusEnum;


@Service
@Transactional
public class PointService {
    
    private final PartnerRepository partnerRepository;
    private final UserRepository userRepository;
    private final PointHstRepository pointHstRepository;

    @Autowired
    public PointService(PartnerRepository partnerRepository, UserRepository userRepository, PointHstRepository pointHstRepository){
        this.partnerRepository = partnerRepository;
        this.userRepository = userRepository;
        this.pointHstRepository = pointHstRepository;
    }

    @Transactional
    public Long usePoint(PointDto usePointDto) {

        var partner = getPartner(usePointDto.getPartnerId());
        var user = getUser(usePointDto.getBarcodeNo());

        if(user.getPoint().size() <= 0){
            throw new MemberShipRuntimeException(StatusEnum.EXCEED_USE_POINT);
        }

        Point point = getUsePoint(user, partner, usePointDto);

        user.add(point);

        userRepository.save(user);

        savePointHst(user, partner, "use");

        return point.getPoint();
    }

    @Transactional
    public Long savePoint(PointDto savePointDto) {
        
        var partner = getPartner(savePointDto.getPartnerId());
        var user = getUser(savePointDto.getBarcodeNo());

        Point point = getSavePoint(user, partner, savePointDto);

        user.add(point);

        userRepository.save(user);

        savePointHst(user, partner, "save");

        return point.getPoint();
    }

    private Partner getPartner(String partnerId){
        return partnerRepository.findById(partnerId)
                                .orElseThrow(() -> new MemberShipRuntimeException(StatusEnum.NOT_REGISTER_PARTNER));
    }
    
    private User getUser(String barcodeNo){
        return userRepository.findByBarcodeNo(barcodeNo)
                             .orElseThrow(() -> new MemberShipRuntimeException(StatusEnum.NOT_REGISTER_BARCODE));
    }

    private Point getSavePoint(User user, Partner partner, PointDto savePointDto){
        Point point = new Point();
        String id = "";

        long amountPoint = savePointDto.getPoint();

        for(int i = 0; i < user.getPoint().size(); i++){
            if(user.getPoint().get(i).getState().equals(partner.getState())){
                amountPoint = getAmountPoint("save", user.getPoint().get(i).getPoint(), savePointDto.getPoint());
                id = user.getPoint().get(i).getId();
            }
        }

        point = Point.builder().id(id).state(partner.getState()).point(amountPoint).build();

        return point;
    }

    private Point getUsePoint(User user, Partner partner, PointDto usePointDto){

        Point point = new Point();
        for(int i = 0; i < user.getPoint().size(); i++){
            if(user.getPoint().get(i).getState().equals(partner.getState())){
                
                verifyExceedPoint(user.getPoint().get(i), usePointDto.getPoint());
                
                long amountPoint = getAmountPoint("use", user.getPoint().get(i).getPoint(), usePointDto.getPoint());
                point = Point.builder().state(partner.getState()).point(amountPoint).build();
            }
        }
        return point;
    }

    private void verifyExceedPoint(Point currentPoint, long usePoint){
        if(currentPoint.getPoint() < usePoint){
            throw new MemberShipRuntimeException(StatusEnum.EXCEED_USE_POINT);
        }
    }

    private long getAmountPoint(String type, long currentPoint, long savePoint){
        return type.equals("save") ? currentPoint + savePoint : currentPoint - savePoint;
    }

    @Transactional
    private void savePointHst(User user, Partner partner, String purpose){
        PointHst pointHst = PointHst
                            .builder()
                            .barcodeNo(user.getBarcodeNo())
                            .approvedAt(LocalDateTime.now())
                            .gubun(purpose)
                            .partnerName(partner.getPartnerName())
                            .state(partner.getState())
                            .build();
                            
        pointHstRepository.save(pointHst);
    }

}
