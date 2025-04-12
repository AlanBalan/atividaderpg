package com.example.atvdcrudrpg.Repository;

import com.example.atvdcrudrpg.Model.usermodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userrepository extends JpaRepository<usermodel, Long> {
}
