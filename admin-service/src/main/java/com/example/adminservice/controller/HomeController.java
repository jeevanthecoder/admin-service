package com.example.adminservice.controller;

import com.example.adminservice.Dao.AdminRepository;
import com.example.adminservice.Dao.TrainRepository;
import com.example.adminservice.entity.AdminAccess;
import com.example.adminservice.entity.TrainDetails;
import com.example.adminservice.service.AdminService;
import com.example.adminservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainService trainService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/current-admin")
    public String getLoggedInAdmin(Principal principal){
        return principal.getName();
    }

    @PostMapping("/upload-TrainDetails/{adminName}")
    public TrainDetails uploadTrainDetails(@RequestBody TrainDetails trainDetails,@PathVariable("adminName") String adminName){
        AdminAccess adminAccess = this.adminRepository.findAdminAccessByAdminName(adminName);

trainDetails.setAdminAccess(adminAccess);
adminAccess.getTrainDetails().add(trainDetails);
        return trainRepository.save(trainDetails);

    }

    @GetMapping("/allTrains")
    public List<TrainDetails> getAllTrainDetails(){
        return trainRepository.findAll();
    }

    @GetMapping("/get-train/{trainNumber}")
    public ResponseEntity<TrainDetails> getTrainByTrainNumber(@PathVariable("trainNumber")String trainNumber){
        TrainDetails trainDetails = this.trainRepository.findTrainDetailsByTrainNumber(trainNumber);
        if(trainDetails==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.ok(trainDetails);
        }

    }

    @PutMapping("/update-TrainDetails/{trainName}")
    public ResponseEntity<Void> updateTrainDetails(@RequestBody TrainDetails trainDetails,@PathVariable("trainName") String trainName){
        if(trainDetails==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            this.trainService.updateTrainDetails(trainDetails, trainName);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @RequestMapping(value = "/delete-TrainDetails/{trainName}",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ResponseEntity<Void> deleteTrainDetails(@PathVariable("trainName")String trainName){
        if(trainName==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            this.trainService.deleteTrainDetails(trainName);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }


}
