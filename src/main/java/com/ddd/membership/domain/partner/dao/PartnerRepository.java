package com.ddd.membership.domain.partner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddd.membership.domain.partner.domain.Partner;

public interface PartnerRepository extends JpaRepository<Partner, String>{
    
}
