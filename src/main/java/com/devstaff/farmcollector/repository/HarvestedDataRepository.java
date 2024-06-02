package com.devstaff.farmcollector.repository;

import com.devstaff.farmcollector.model.HarvestedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestedDataRepository extends JpaRepository<HarvestedData, Long> {
}
