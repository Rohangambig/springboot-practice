package com.entire.demo.repository;

import com.entire.demo.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<userModel,Long> {
}
