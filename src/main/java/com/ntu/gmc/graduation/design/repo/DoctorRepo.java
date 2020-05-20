package com.ntu.gmc.graduation.design.repo;

import com.ntu.gmc.graduation.design.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/20 下午7:49
 * @contact shadowl91@163.com
 */
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Doctor findByAccount(String account);
    Doctor findById(int doctorid);

}
