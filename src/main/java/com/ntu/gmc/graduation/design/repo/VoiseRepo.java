package com.ntu.gmc.graduation.design.repo;

import com.ntu.gmc.graduation.design.domain.Patient;
import com.ntu.gmc.graduation.design.domain.Voise;
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
public interface VoiseRepo extends JpaRepository<Voise, Integer> {
    @Query(value = "select * from t_voise where patientid = ?",nativeQuery = true)
    public List<Voise> getAll(String patientId);

    @Query(value = "select * from t_voise where patientid = ? and start = ? and end = ?",nativeQuery = true)
    public Voise getOneVoise(int patientid, String start, String end);
}
