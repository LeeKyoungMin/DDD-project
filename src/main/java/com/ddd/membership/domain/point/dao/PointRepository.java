package com.ddd.membership.domain.point.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddd.membership.domain.point.domain.Point;

public interface PointRepository extends JpaRepository<Point, String>{
}
