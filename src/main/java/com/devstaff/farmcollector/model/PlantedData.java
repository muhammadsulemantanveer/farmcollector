package com.devstaff.farmcollector.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "planted_data")
@Getter
@Setter
public class PlantedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planting_area")
    private double plantingArea;

    @Column(name = "expected_product")
    private double expectedProduct;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    @JsonBackReference
    private Crop crop;

}
