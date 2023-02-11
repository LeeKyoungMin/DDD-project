package com.ddd.membership.domain.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddd.membership.domain.user.dao.UserRepository;
import com.ddd.membership.domain.user.domain.User;
import com.ddd.membership.domain.user.dto.UserDto;
import com.ddd.membership.global.exception.MemberShipRuntimeException;
import com.ddd.membership.global.response.StatusEnum;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createUser(UserDto userDto) {
        if(userDto.getName().isBlank() || userDto.getPwd().isBlank()){
            throw new MemberShipRuntimeException(StatusEnum.INVALID_REQUEST);
        }

        User newUser = new User();
        newUser = userDto.convertDtoToEntity();

        User user = userRepository.save(newUser);

        return user.getId();
    }

    @Transactional
    public String createBarcode(Long userId) {

        User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new MemberShipRuntimeException(StatusEnum.USER_NOT_FOND));

        //사용자의 바코드가 이미 존재할때 기존 바코드를 return한다.
        if(user.getBarcodeNo() != null && !user.getBarcodeNo().isBlank()){
            return user.getBarcodeNo();
        }
        
        String barcode = user.issueBardcode();

        user.setBarcodeNo(barcode);
        userRepository.save(user);

        return barcode;
    }
    
}
