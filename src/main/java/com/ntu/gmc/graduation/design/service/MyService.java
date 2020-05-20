package com.ntu.gmc.graduation.design.service;

import com.ntu.gmc.graduation.design.domain.Doctor;
import com.ntu.gmc.graduation.design.domain.Patient;
import com.ntu.gmc.graduation.design.domain.Voise;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Zed
 * @date 2020/5/20 下午7:51
 * @contact shadowl91@163.com
 */
public interface MyService {
    Doctor createDoc(Doctor doctor);

    Doctor loginDoc(Doctor doctor);

    Patient createPatient(Patient patient);

    Patient loginPatient(Patient patient);

    Voise createVoise(Voise voise, MultipartFile file) throws IOException;

    List<Voise> findVoise(String patientId);

    File getOneVoise(Voise voise, HttpServletResponse resp) throws IOException;

    List<Patient> getAllPatients(Doctor doctor);

    void removePatient(Patient patient);

    Patient updatePatient(Patient patient);

    List<Doctor> getAllDoctors();

}
