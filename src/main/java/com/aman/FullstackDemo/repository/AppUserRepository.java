package com.aman.FullstackDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aman.FullstackDemo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    public AppUser findByEmail(String email);
}
