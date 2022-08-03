package com.homedecor.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

}
