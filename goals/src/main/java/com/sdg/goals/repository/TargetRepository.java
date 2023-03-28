package com.sdg.goals.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sdg.goals.model.Target;



public interface TargetRepository extends JpaRepository<Target, Integer> {
}