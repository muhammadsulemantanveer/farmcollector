package com.devstaff.farmcollector.controller;

import com.devstaff.farmcollector.exception.RecordNotFoundException;
import com.devstaff.farmcollector.model.Crop;
import com.devstaff.farmcollector.model.Farm;
import com.devstaff.farmcollector.model.HarvestedData;
import com.devstaff.farmcollector.model.PlantedData;
import com.devstaff.farmcollector.service.FarmService;
import com.devstaff.farmcollector.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/farm")
public class FarmController {

    private final FarmService farmService;
    private final ReportService reportService;
    public FarmController(FarmService farmService, ReportService reportService) {
        this.farmService = farmService;
        this.reportService = reportService;
    }

    @PostMapping("/create")
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @PostMapping("/{farmId}/crops")
    public Crop addCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
        Farm farm = farmService.findFarmById(farmId).orElseThrow(() -> new RecordNotFoundException("Farm not found"));
        crop.setFarm(farm);
        return farmService.saveCrop(crop);
    }

    @PostMapping("/{farmId}/crops/{cropId}/planted")
    public PlantedData addPlantedData(@PathVariable Long farmId, @PathVariable Long cropId, @RequestBody PlantedData plantedData) {
        Crop crop = farmService.findCropById(cropId).orElseThrow(() -> new RecordNotFoundException("Crop or Farm not found"));
        plantedData.setCrop(crop);
        return farmService.savePlantedData(plantedData);
    }

    @PostMapping("/{farmId}/crops/{cropId}/harvested")
    public HarvestedData addHarvestedData(@PathVariable Long farmId, @PathVariable Long cropId, @RequestBody HarvestedData harvestedData) {
        Crop crop = farmService.findCropById(cropId).orElseThrow(() -> new RecordNotFoundException("Crop or Farm not found"));
        harvestedData.setCrop(crop);
        return farmService.saveHarvestedData(harvestedData);
    }

    @GetMapping("/{farmId}/report")
    public String getFarmReport(@PathVariable Long farmId) {
        return reportService.generateReportForFarm(farmId);
    }

    @GetMapping("/report")
    public String getCropTypeReport(@RequestParam String cropName) {
        return reportService.generateReportForCropType(cropName);
    }
}
