package com.dental.repository;

import com.dental.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Page<Appointment> findAllByOrderByDateDesc(Pageable pageable);

    Page<Appointment> findAllByDate(Date date, Pageable pageable);

    Page<Appointment> findAllByStatus(String status, Pageable pageable);

    Page<Appointment> findAllByStatusAndDate(String status, Date date, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment p SET p.status = ?1 WHERE p.appointment_id = ?2", nativeQuery = true)
    void updateAppointmentStatus(String status, int userId);
}