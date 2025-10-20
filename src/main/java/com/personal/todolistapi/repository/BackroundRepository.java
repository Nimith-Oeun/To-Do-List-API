package com.personal.todolistapi.repository;

import com.personal.todolistapi.model.Background;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackroundRepository extends JpaRepository<Background, Long> {

//    // get the latest file by profile id
//    Optional<Background> findFirstByProfileIdOrderByIdDesc(Long profileId);
}
