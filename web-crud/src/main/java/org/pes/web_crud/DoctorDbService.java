package org.pes.web_crud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import jakarta.transaction.Transactional;

// it can be autowired
@Service
public class DoctorDbService {
    // dependency injection
    @Autowired
    DoctorDbRepo repository;

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
