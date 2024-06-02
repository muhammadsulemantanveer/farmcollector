package com.devstaff.farmcollector.service;


import com.devstaff.farmcollector.model.*;
import com.devstaff.farmcollector.repository.CropRepository;
import com.devstaff.farmcollector.repository.FarmRepository;
import com.devstaff.farmcollector.repository.HarvestedDataRepository;
import com.devstaff.farmcollector.repository.PlantedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final PlantedDataRepository plantedDataRepository;
    private final HarvestedDataRepository harvestedDataRepository;

    public FarmService(FarmRepository farmRepository,
                       CropRepository cropRepository,
                       PlantedDataRepository plantedDataRepository,
                       HarvestedDataRepository harvestedDataRepository) {
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
        this.plantedDataRepository = plantedDataRepository;
        this.harvestedDataRepository = harvestedDataRepository;
    }


    public Farm saveFarm(Farm farm) {return farmRepository.save(farm);}
    public Crop saveCrop(Crop crop) {return cropRepository.save(crop);}
    public Optional<Farm> findFarmById(Long id) {return farmRepository.findById(id);}
    public List<Farm> findAllFarms() {return farmRepository.findAll();}
    public Optional<Crop> findCropById(Long id) {return cropRepository.findById(id);}
    public List<Crop> findAllCrops() {return cropRepository.findAll();}
    public PlantedData savePlantedData(PlantedData plantedData) {return plantedDataRepository.save(plantedData);}
    public HarvestedData saveHarvestedData(HarvestedData harvestedData) {return harvestedDataRepository.save(harvestedData);}
}