package org.pes.web_crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

// it can be autowired
@Service
public class DoctorDbService {
    // dependency injection
    @Autowired
    DoctorDbRepo repository;

    public void changeByExperience(int exp){
        repository.updateByExperience(exp);
    }

    @Transactional
    public List<String> readDepartments(String name){
        return repository.findAllByName(name);
    }

    @Transactional
    public List<String> readNames(String department){
        return repository.findAllByDepartment(department);
    }

    @Transactional
    public List<Doctor> readAboveExpeerience(int exp){
        return repository.findAllByExperienceAbove(exp);
    }

    @Transactional
    public List<Doctor> readAllExperience(int exp){
        return repository.findAllByDoctorExperience(exp);
    }

    @Transactional
    public Optional<Doctor> readById(int empid){
        return repository.findById(empid);
    }

    @Transactional
    public List<Doctor> readAll() {
        return repository.findAll();
    }

    @Transactional
    public String insert(Doctor doctor) {
        String msg = repository.save(doctor).getDoctorName();
        return msg+" recruited";
    }

    @Transactional
    public String terminate(int doctorId) {
        repository.deleteById(doctorId);
        return "terminated";

    }

    @Transactional
    public String update(Doctor doctor) {
        repository.save(doctor).getDoctorName();
        return "updated";
    }
}
