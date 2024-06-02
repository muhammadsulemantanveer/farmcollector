package com.devstaff.farmcollector.repository;

import com.devstaff.farmcollector.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
