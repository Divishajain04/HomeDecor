package com.homedecor.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
