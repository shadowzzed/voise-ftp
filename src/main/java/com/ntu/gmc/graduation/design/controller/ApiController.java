package com.ntu.gmc.graduation.design.controller;

import com.alibaba.fastjson.JSON;
import com.ntu.gmc.graduation.design.domain.Doctor;
import com.ntu.gmc.graduation.design.domain.Patient;
import com.ntu.gmc.graduation.design.domain.Voise;
import com.ntu.gmc.graduation.design.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Zed
 * @date 2020/5/20 下午7:55
 * @contact shadowl91@163.com
 */
@RestController
@Slf4j
public class ApiController {

    @Autowired
    private MyService myService;

    @PostMapping("/api/v1/doctor")
    public String createDoc(Doctor doc) {
        log.info("get param = {}", doc);
        Doctor doctor = myService.createDoc(doc);
        if (doctor == null)
            return null;
        return JSON.toJSONString(doctor);
    }

    @PostMapping("/api/v1/doctor/1")
    public String loginDoc(Doctor doc) {
        log.info("get param = {}", doc);
        Doctor doctor = myService.loginDoc(doc);
        if (doctor == null)
            return null;
        return JSON.toJSONString(doctor);
    }

    @PostMapping("/api/v1/patient")
    public String createPatient(Patient patient) {
        log.info("get param = {}", patient);
        Patient patient_db = myService.createPatient(patient);
        if (patient_db == null)
            return null;
        return JSON.toJSONString(patient_db);
    }

    @PostMapping("/api/v1/patient/1")
    public String loginPatient(Patient patient) {
        log.info("get param = {}", patient);
        Patient patient_db = myService.loginPatient(patient);
        if (patient_db == null)
            return null;
        return JSON.toJSONString(patient_db);
    }

    @PostMapping("/api/v1/voise")
    public String uploadVoise(Voise voise, @RequestParam("file")MultipartFile file) throws IOException {
        log.info("get param = {}", voise);
        Voise voise1 = myService.createVoise(voise, file);
        if (voise1 == null)
            return null;
        return JSON.toJSONString(voise1);
    }

    @GetMapping("/api/v1/voise/all/{patientid}")
    public String getAllVoises(@PathVariable("patientid") String patientId) {
        log.info("get param = {}", patientId);
        List<Voise> voise = myService.findVoise(patientId);
        if (voise == null)
            return null;
        return JSON.toJSONString(voise);
    }

    @GetMapping("/api/v1/doctor/all/")
    public String getAllPatients(Doctor doctor) {
        log.info("get param = {}", doctor);
        List<Patient> patients = myService.getAllPatients(doctor);
        if (patients == null || patients.size() < 1)
            return null;
        return JSON.toJSONString(patients);

    }

    @GetMapping("/api/v1/voise/")
    public String downloadVoise(Voise voise, HttpServletResponse resp) throws IOException {
        log.info("get param = {}", voise);
        File file = myService.getOneVoise(voise, resp);
        if (file == null)
            return null;
        return "success";


    }

}
