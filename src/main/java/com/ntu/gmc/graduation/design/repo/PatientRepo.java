package com.ntu.gmc.graduation.design.repo;

import com.ntu.gmc.graduation.design.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/20 下午7:50
 * @contact shadowl91@163.com
 */
@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByAccount(String account);

    Patient findById(int id);

    @Query(value = "select doctorid where patientid = ?", nativeQuery = true)
    int findDoctorIdById(int id);


    @Query(value = "select * where doctorid = ?",nativeQuery = true)
    List<Patient> getAllPatients(int doctorid);
}
