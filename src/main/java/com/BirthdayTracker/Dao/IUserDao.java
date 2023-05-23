package com.BirthdayTracker.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BirthdayTracker.Entities.User;

public interface IUserDao extends JpaRepository<User, Integer>{

}
