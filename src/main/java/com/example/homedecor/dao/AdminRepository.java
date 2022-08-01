package com.example.homedecor.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.homedecor.dto.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Optional<Admin> findByAdminIDAndAdminPassword(Integer adminId, String password);

}
