package com.example.adminservice.service;

import com.example.adminservice.Dao.AdminRepository;
import com.example.adminservice.Dao.TrainRepository;
import com.example.adminservice.entity.AdminAccess;
import com.example.adminservice.entity.TrainDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public void updateTrainDetails(TrainDetails trainDetails,String TrainName){
        TrainDetails trainDetails1=trainRepository.findTrainDetailsByTrainName(TrainName);
trainDetails1.setTrainName(trainDetails.trainName);
trainDetails1.setTrainNumber(trainDetails.trainNumber);
trainDetails1.setSource(trainDetails.getSource());
trainDetails1.setDest(trainDetails1.getDest());
    }

    @Transactional
    public void deleteTrainDetails(String TrainName){

        TrainDetails trainDetails = this.trainRepository.findTrainDetailsByTrainName(TrainName);
        AdminAccess adminAccess = trainDetails.getAdminAccess();
        adminAccess.getTrainDetails().remove(trainDetails);

        this.trainRepository.deleteByTrainName(TrainName);

    }

    @Transactional
    public TrainDetails TrainNameByTrainNumber(String trainNumber){
        TrainDetails trainDetails = this.trainRepository.findTrainDetailsByTrainNumber(trainNumber);
        if(trainDetails==null){
            return null;
        }else{
            return trainDetails;
        }
    }
}
