-- Create the database
CREATE DATABASE farmcollector;

USE farmcollector;

CREATE TABLE farm (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create Crop table
CREATE TABLE crop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    farm_id BIGINT,
    FOREIGN KEY (farm_id) REFERENCES Farm(id)
);

-- Create PlantedData table
CREATE TABLE planted_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    planting_area DOUBLE NOT NULL,
    expected_product DOUBLE NOT NULL,
    crop_id BIGINT,
    FOREIGN KEY (crop_id) REFERENCES Crop(id)
);

-- Create HarvestedData table
CREATE TABLE harvested_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    actual_product DOUBLE NOT NULL,
    crop_id BIGINT,
    FOREIGN KEY (crop_id) REFERENCES Crop(id)
);
