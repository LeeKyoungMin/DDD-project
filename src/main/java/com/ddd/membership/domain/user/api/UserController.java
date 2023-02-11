package com.ddd.membership.domain.user.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.membership.domain.user.application.UserService;
import com.ddd.membership.domain.user.dto.UserDto;
import com.ddd.membership.global.response.Response;

@RestController
@RequestMapping(value = "/membership")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * 테스트를 위한 회원 생성 API
     * @param userDto
     * @return userId
     */
    @PostMapping("/createUser")
    public Response<Object> createUser(@RequestBody UserDto userDto){

        Long userId = userService.createUser(userDto);

        return new Response<>().responseOk(userId);
    }

    /**
    * 통합 바코드 발급 API
    * @param userId
    * @return barcode
    */
    @PostMapping("/createBarcode")
    public Response<Object> createBarcode(@RequestParam("userId") Long userId){
        
        String barcode = userService.createBarcode(userId);

        return new Response<>().responseOk(barcode);
    }

}
