package com.devstaff.farmcollector.repository;

import com.devstaff.farmcollector.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findAllByFarmId(Long farmId);
    List<Crop> findAllByName(String name);
}