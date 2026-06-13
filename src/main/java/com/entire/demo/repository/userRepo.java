package com.entire.demo.repository;

import com.entire.demo.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepo extends JpaRepository<userModel,Long> {
    @Query("SELECT u FROM userModel u where u.email= :email")
    userModel findByEmail(@Param("email") String email);
}
