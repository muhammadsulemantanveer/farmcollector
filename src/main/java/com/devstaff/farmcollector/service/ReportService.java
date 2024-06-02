package com.devstaff.farmcollector.service;

import com.devstaff.farmcollector.exception.RecordNotFoundException;
import com.devstaff.farmcollector.model.Crop;
import com.devstaff.farmcollector.model.PlantedData;
import com.devstaff.farmcollector.model.HarvestedData;
import com.devstaff.farmcollector.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportService {

    private final CropRepository cropRepository;
    public ReportService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public String generateReportForFarm(Long farmId) {
        List<Crop> crops = cropRepository.findAllByFarmId(farmId);
        if(crops == null || crops.isEmpty()){
            throw new RecordNotFoundException("Crop or Farm not found");
        }
        StringBuilder report = new StringBuilder("Farm Report:\n");
        for (Crop crop : crops) {
            double expected = crop.getPlantedData().stream().mapToDouble(PlantedData::getExpectedProduct).sum();
            double actual = crop.getHarvestedData().stream().mapToDouble(HarvestedData::getActualProduct).sum();
            report.append("Crop: ").append(crop.getName())
                    .append(", Expected: ").append(expected)
                    .append(", Actual: ").append(actual)
                    .append("\n");
        }

        return report.toString();
    }

    public String generateReportForCropType(String cropName) {
        List<Crop> crops = cropRepository.findAllByName(cropName);
        StringBuilder report = new StringBuilder("Crop Type Report:\n");
        if(crops == null || crops.isEmpty()){
            throw new RecordNotFoundException("Crop or Farm not found");
        }
        for (Crop crop : crops) {
            double expected = crop.getPlantedData().stream().mapToDouble(PlantedData::getExpectedProduct).sum();
            double actual = crop.getHarvestedData().stream().mapToDouble(HarvestedData::getActualProduct).sum();
            report.append("Farm: ").append(crop.getFarm().getName())
                    .append(", Expected: ").append(expected)
                    .append(", Actual: ").append(actual)
                    .append("\n");
        }

        return report.toString();
    }
}
