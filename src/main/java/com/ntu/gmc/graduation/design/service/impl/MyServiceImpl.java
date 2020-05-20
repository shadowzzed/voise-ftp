package com.ntu.gmc.graduation.design.service.impl;

import com.ntu.gmc.graduation.design.configuration.MyConfig;
import com.ntu.gmc.graduation.design.domain.Doctor;
import com.ntu.gmc.graduation.design.domain.Patient;
import com.ntu.gmc.graduation.design.domain.Voise;
import com.ntu.gmc.graduation.design.repo.DoctorRepo;
import com.ntu.gmc.graduation.design.repo.PatientRepo;
import com.ntu.gmc.graduation.design.repo.VoiseRepo;
import com.ntu.gmc.graduation.design.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Zed
 * @date 2020/5/20 下午8:12
 * @contact shadowl91@163.com
 */
@Service
@Slf4j
public class MyServiceImpl implements MyService {
    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    VoiseRepo voiseRepo;

    @Autowired
    MyConfig config;

    @Override
    public Doctor createDoc(Doctor doctor) {
        if (StringUtils.isEmpty(doctor.getPassword()) ||
        StringUtils.isEmpty(doctor.getAccount()) ||
        StringUtils.isEmpty(doctor.getName()) ||
        StringUtils.isEmpty(doctor.getPhone())) {
            return null;
        }
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor loginDoc(Doctor doctor) {
        if (StringUtils.isEmpty(doctor.getAccount()) || StringUtils.isEmpty(doctor.getPassword()))
            return null;
        Doctor doctor1 = doctorRepo.findByAccount(doctor.getAccount());
        if (doctor1 == null)
            return null;
        if (!doctor1.getPassword().equals(doctor.getPassword()))
            return null;
        return doctor1;
    }

    @Override
    public Patient createPatient(Patient patient) {
        if (StringUtils.isEmpty(patient.getAccount()) ||
                StringUtils.isEmpty(patient.getAge()) ||
                StringUtils.isEmpty(patient.getDoctorid()) ||
                StringUtils.isEmpty(patient.getIllness()) ||
                StringUtils.isEmpty(patient.getPhone()) ||
                StringUtils.isEmpty(patient.getName()) ||
                StringUtils.isEmpty(patient.getPassword()))
            return null;
        return patientRepo.save(patient);
    }

    @Override
    public Patient loginPatient(Patient patient) {
        if (StringUtils.isEmpty(patient.getAccount()) || StringUtils.isEmpty(patient.getPassword()))
            return null;
        Patient patient1 = patientRepo.findByAccount(patient.getAccount());
        if (patient1 == null)
            return null;
        if (!patient1.getPassword().equals(patient.getPassword()))
            return null;
        return patient1;
    }

    @Override
    public Voise createVoise(Voise voise, MultipartFile file) throws IOException {
        if (file == null || !this.judgeParam(voise))
            return null;
        Patient patient = patientRepo.findById(voise.getPatientid());
        if (patient == null) {
            log.error("patient is null");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(currentTimeMillis);
        String path = config.filePath + "/" + date;
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        File file_target = new File(path + "/" + voise.getPatientid() + voise.getStart() + voise.getEnd());
        log.info("file path = {}", file_target.getPath());
        byte[] bytes = new byte[1024];
        InputStream inputStream = file.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(file_target);
        int n = 0;
        while ((n = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, n);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        voise.setUrl(file_target.getPath());
        return voiseRepo.save(voise);
    }

    @Override
    public List<Voise> findVoise(String patientId) {
        if (StringUtils.isEmpty(patientId))
            return null;
        return voiseRepo.getAll(patientId);
    }

    @Override
    public File getOneVoise(Voise voise, HttpServletResponse resp) throws IOException {
        Voise oneVoise = voiseRepo.getOneVoise(voise.getPatientid(), voise.getStart(), voise.getEnd());
        if (oneVoise == null)
            return null;
        File file = new File(oneVoise.getUrl());
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition", "attachment;file=\"" + voise.getUrl() + "\"");
        ServletOutputStream outputStream = resp.getOutputStream();
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0 , n);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return file;
    }

    @Override
    public List<Patient> getAllPatients(Doctor doctor) {
        if (StringUtils.isEmpty(doctor.getId()))
            return null;
        return patientRepo.getAllPatients(doctor.getId());
    }

    private Boolean judgeParam(Voise voise) {
        if (StringUtils.isEmpty(voise.getEnd()) ||
                StringUtils.isEmpty(voise.getPatientid()) ||
                StringUtils.isEmpty(voise.getStart()))
            return false;
        return true;
    }
}
