package com.homedecor.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Optional<Admin> findByAdminIdAndAdminPassword(Integer adminId, String password);

}
