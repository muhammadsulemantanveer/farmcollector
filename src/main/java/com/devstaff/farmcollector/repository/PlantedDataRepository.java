package com.devstaff.farmcollector.repository;

import com.devstaff.farmcollector.model.PlantedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantedDataRepository extends JpaRepository<PlantedData, Long> {
}
