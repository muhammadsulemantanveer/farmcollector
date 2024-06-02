package com.farmcollector;

import com.devstaff.farmcollector.FarmCollectorApplication;
import com.devstaff.farmcollector.model.Crop;
import com.devstaff.farmcollector.model.Farm;
import com.devstaff.farmcollector.model.HarvestedData;
import com.devstaff.farmcollector.model.PlantedData;
import com.devstaff.farmcollector.service.FarmService;
import com.devstaff.farmcollector.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = FarmCollectorApplication.class)
@AutoConfigureMockMvc
public class FarmCollectorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FarmService farmService;

	@MockBean
	private ReportService reportService;

	@Test
	public void testCreateFarm() throws Exception {
		Farm farm = new Farm();
		farm.setName("Test Farm");

		when(farmService.saveFarm(any(Farm.class))).thenReturn(farm);

		mockMvc.perform(post("/api/farms")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Test Farm\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Test Farm"));
	}

	@Test
	public void testAddCrop() throws Exception {
		Farm farm = new Farm();
		farm.setId(1L);
		farm.setName("Test Farm");

		Crop crop = new Crop();
		crop.setName("Corn");
		crop.setFarm(farm);

		when(farmService.findFarmById(1L)).thenReturn(Optional.of(farm));
		when(farmService.saveCrop(any(Crop.class))).thenReturn(crop);

		mockMvc.perform(post("/api/farms/1/crops")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Corn\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Corn"));
	}

	@Test
	public void testAddPlantedData() throws Exception {
		Farm farm = new Farm();
		farm.setId(1L);
		farm.setName("Test Farm");

		Crop crop = new Crop();
		crop.setId(1L);
		crop.setName("Corn");
		crop.setFarm(farm);

		PlantedData plantedData = new PlantedData();
		plantedData.setPlantingArea(10);
		plantedData.setExpectedProduct(100);
		plantedData.setCrop(crop);

		when(farmService.findCropById(1L)).thenReturn(Optional.of(crop));
		when(farmService.savePlantedData(any(PlantedData.class))).thenReturn(plantedData);

		mockMvc.perform(post("/api/farms/1/crops/1/planted")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"plantingArea\": 10, \"expectedProduct\": 100}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.plantingArea").value(10))
				.andExpect(jsonPath("$.expectedProduct").value(100));
	}

	@Test
	public void testAddHarvestedData() throws Exception {
		Farm farm = new Farm();
		farm.setId(1L);
		farm.setName("Test Farm");

		Crop crop = new Crop();
		crop.setId(1L);
		crop.setName("Corn");
		crop.setFarm(farm);

		HarvestedData harvestedData = new HarvestedData();
		harvestedData.setActualProduct(90);
		harvestedData.setCrop(crop);

		when(farmService.findCropById(1L)).thenReturn(Optional.of(crop));
		when(farmService.saveHarvestedData(any(HarvestedData.class))).thenReturn(harvestedData);

		mockMvc.perform(post("/api/farms/1/crops/1/harvested")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"actualProduct\": 90}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.actualProduct").value(90));
	}

	@Test
	public void testGetFarmReport() throws Exception {
		String report = "Farm Report:\nCrop: Corn, Expected: 100.0, Actual: 90.0\n";

		when(reportService.generateReportForFarm(1L)).thenReturn(report);

		mockMvc.perform(get("/api/farms/1/report"))
				.andExpect(status().isOk())
				.andExpect(content().string(report));
	}

	@Test
	public void testGetCropTypeReport() throws Exception {
		String report = "Crop Type Report:\nFarm: Test Farm, Expected: 100.0, Actual: 90.0\n";

		when(reportService.generateReportForCropType("Corn")).thenReturn(report);

		mockMvc.perform(get("/api/farms/report")
						.param("cropName", "Corn"))
				.andExpect(status().isOk())
				.andExpect(content().string(report));
	}
}
