package com.ddd.membership.domain.point.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.membership.domain.point.application.PointService;
import com.ddd.membership.domain.point.dto.PointDto;
import com.ddd.membership.global.response.Response;

@RestController
@RequestMapping("/membership")
public class PointController {
    
    private PointService pointService;

    public PointController(PointService pointService){
        this.pointService = pointService;
    }

    /**
     * 포인트 사용 API 
     * @param usePointDto
     * @return Response
     */
    @PostMapping("/usePoint")
    public Response<Object> usePoint(@RequestBody PointDto usePointDto){

        pointService.usePoint(usePointDto);

        return new Response<>().responseOk();
    }

    /**
     * 포인트 적립 API
     * @param savePointDto
     * @return Response
     */
    @PostMapping("/savePoint")
    public Response<Object> savePoint(@RequestBody PointDto savePointDto){
        
        pointService.savePoint(savePointDto);

        return new Response<>().responseOk();
    }
}
