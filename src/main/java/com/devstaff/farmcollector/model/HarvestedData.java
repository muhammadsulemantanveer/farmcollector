package com.devstaff.farmcollector.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "harvested_data")
@Getter
@Setter
public class HarvestedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actual_product")
    private double actualProduct;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    @JsonBackReference
    private Crop crop;

}
