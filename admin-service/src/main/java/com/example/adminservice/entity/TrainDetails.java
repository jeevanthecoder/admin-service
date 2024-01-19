package com.example.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TrainDetails {

    @Id
    @GeneratedValue
    public int t_Id;

    @Column(unique = true)
    public String trainNumber;

    @Column(unique = true)
    public String trainName;

    public String ClassType;

    public String Source;
    public String Dest;

    public Date DateOfJourney;

    @ManyToOne()
    @JsonBackReference
    private AdminAccess adminAccess;

    public TrainDetails(String trainNumber, String trainName, String classType, String source, String dest, Date dateOfJourney,AdminAccess adminAccess) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        ClassType = classType;
        Source = source;
        Dest = dest;
        DateOfJourney = dateOfJourney;
        this.adminAccess=adminAccess;
    }
}
