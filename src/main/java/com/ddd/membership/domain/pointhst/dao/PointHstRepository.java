package com.ddd.membership.domain.pointhst.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddd.membership.domain.pointhst.domain.PointHst;

public interface PointHstRepository extends JpaRepository<PointHst, String>{

    List<PointHst> findByBarcodeNoAndApprovedAtBetween(String barcodeNo, LocalDateTime startTm, LocalDateTime endTm);
}
