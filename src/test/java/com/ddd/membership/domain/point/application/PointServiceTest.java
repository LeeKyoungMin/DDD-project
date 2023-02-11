package com.ddd.membership.domain.point.application;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.ActiveProfiles;

import com.ddd.membership.domain.category.domain.Category;
import com.ddd.membership.domain.partner.dao.PartnerRepository;
import com.ddd.membership.domain.partner.domain.Partner;
import com.ddd.membership.domain.point.application.PointService;
import com.ddd.membership.domain.point.domain.Point;
import com.ddd.membership.domain.point.dto.PointDto;
import com.ddd.membership.domain.pointhst.dao.PointHstRepository;
import com.ddd.membership.domain.user.dao.UserRepository;
import com.ddd.membership.domain.user.domain.User;

@TestInstance(PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("PointService Unit Tests")
public class PointServiceTest {

    private PartnerRepository partnerRepository;
    private UserRepository userRepository;
    private PointHstRepository pointHstRepository;
    private PointService pointService;

    @BeforeAll
    public void init() {
        partnerRepository = mock(PartnerRepository.class);
        userRepository = mock(UserRepository.class);
        pointHstRepository = mock(PointHstRepository.class);
        pointService = new PointService(partnerRepository, userRepository, pointHstRepository);
    }

    @DisplayName("[Service] 포인트 적립 테스트")
    @Test
    void testSavePoint() {
        //given
        String partnerId = "oliveyoung";
        String barcodeNo = "123456789";
        long savePoint = 1000L;

        PointDto savePointDto = PointDto
                                    .builder()
                                    .partnerId(partnerId)
                                    .barcodeNo(barcodeNo)
                                    .point(savePoint)
                                    .build();
        User user = User
                    .builder()
                    .barcodeNo(barcodeNo)
                    .build();

        Partner partner = Partner
                          .builder()
                          .id(partnerId)
                          .build();

        Point point = Point
                      .builder()
                      .point(savePoint)
                      .state(Category.COSMETICS)
                      .build();

        //when
        when(partnerRepository.findById(partnerId)).thenReturn(Optional.of(partner));
        when(userRepository.findByBarcodeNo(barcodeNo)).thenReturn(Optional.of(user));

        long amountPoint = pointService.savePoint(savePointDto);

        //then
        assertNotNull(partner);
        assertNotNull(user);
        assertNotNull(point);
        assertNotEquals(30000L, amountPoint);
    }

    @DisplayName("[Service] 포인트 사용 테스트")
    @Test
    void testUsePoint() {
        //given

        //when

        //then
    }
}
