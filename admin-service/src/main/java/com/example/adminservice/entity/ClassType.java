package com.example.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClassType {

    @Id
    @GeneratedValue
    int c_Id;
    String classtype;

    public Long numberOfSeats;

    @ManyToOne()
    @JsonBackReference
    public TrainDetails trainDetails;

    public String getClasstype() {
        return classtype;
    }

    public ClassType setClasstype(String classtype) {
        this.classtype = classtype;
        return this;
    }

    public TrainDetails getTrainDetails() {
        return trainDetails;
    }

    public ClassType setTrainDetails(TrainDetails trainDetails) {
        this.trainDetails = trainDetails;
        return this;
    }

}
