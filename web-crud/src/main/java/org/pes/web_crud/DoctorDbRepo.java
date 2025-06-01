package org.pes.web_crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

/*
 * save
 * findAll
 * findById
 * delete
 * existsById
 * deleteById
 * getById
 */

@Repository
public interface DoctorDbRepo extends JpaRepository<Doctor,Integer>, JpaSpecificationExecutor<Doctor> {
    // user defined non writable logic method
    List<Doctor> findAllByDoctorExperience(int experienece);

    // HQL
    @Query(value = "from Doctor where doctorExperience >= :experienece")
    List<Doctor> findAllByExperienceAbove(int experienece);
    @Query(value = "Select doctorName from Doctor where doctorDepartment = :department")
    List<String> findAllByDepartment(String department);

    // SQL
    @Query(value = "select surgeon_department from surgeon where surgeon_name= :name", nativeQuery = true)
    List<String> findAllByName(String name);

    // HQL for update
    @Query(value="Update Doctor set doctorDepartment='Academic' where doctorExperience >= :experienece")
    @Modifying
    @Transactional
    void updateByExperience(int experienece);

}
