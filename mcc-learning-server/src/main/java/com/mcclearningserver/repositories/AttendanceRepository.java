/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.repositories;

import com.mcclearningserver.entities.Attendance;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO-KL
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByDate(Date date);

    @Query(value = "select * FROM attendance JOIN trainee ON attendance.id_employee = trainee.id_trainee WHERE trainee.batch= :batch  AND attendance.date= :date", nativeQuery = true)
    public List<Attendance> findAllbyBatch(@Param("batch") String batch, @Param("date") Date date);

    @Query(value = "select * from attendance a where a.date between :dateBefore and :dateAfter", nativeQuery = true)
    public List<Attendance> findByRequestReceivedDateBetween(@Param("dateBefore") Date dateBefore,
            @Param("dateAfter") Date dateAfter);

    @Query(value = "select * from attendance a where a.id_employee= :idEmployee and a.date between :dateBefore and :dateAfter", nativeQuery = true)
    public List<Attendance> findAllbyIdDate(@Param("idEmployee") Integer idEmployee,
            @Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);

}
