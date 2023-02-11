package com.ddd.membership.domain.pointhst.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.membership.domain.pointhst.application.PointHstService;
import com.ddd.membership.domain.pointhst.dto.PointHstDto;
import com.ddd.membership.global.response.Response;

@RestController
@RequestMapping("/membership")
public class PointHstController {
    
    private PointHstService pointHstService;

    public PointHstController(PointHstService pointHstService){
        this.pointHstService = pointHstService;
    }

    /**
     * 포인트 내역조회 API
     * @param barcodeNo
     * @param startTm
     * @param endTm
     * @return Resonse<List>
     */
    @GetMapping("/getHsts")
    public Response<List<PointHstDto>> getHsts(@RequestParam("barcodeNo") String barcodeNo
                                              ,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("startTm") LocalDateTime startTm
                                              ,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("endTm") LocalDateTime endTm)
    {
        var pointHstDto = pointHstService.getHsts(barcodeNo, startTm, endTm);
        return new Response<List<PointHstDto>>().responseOk(pointHstDto);
    }
}
