package org.pes.web_crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

// test class
@SpringBootTest
public class TestService {
    @Mock
    private DoctorDbRepo repo;
    @InjectMocks
    private DoctorDbService service;

    static List<Doctor> myList;
    static Doctor doctor1,doctor2;

    @BeforeEach
    void settingUp(){
        MockitoAnnotations.openMocks(this);
        doctor1 =new Doctor();
        doctor1.setDoctorId(12);
        doctor1.setDoctorName("Vinay");
        doctor1.setDoctorDepartment("Physiyo");
        doctor1.setDoctorExperience(7);
        doctor2 =new Doctor();
        doctor2.setDoctorId(88);
        doctor2.setDoctorName("Vijay");
        doctor2.setDoctorDepartment("Chemistry");
        doctor2.setDoctorExperience(9);

        myList = Stream.of(doctor1,doctor2).
        collect(Collectors.toList());
    }

    @Test
    void testFindAll(){
        // mockito
        when(repo.findAll()).thenReturn(myList);
        // test cases
        // assertSame(4, service.readAll().size());
        assertNotSame(4, service.readAll().size());
    }

    @Test
    void testFindById(){
        // mocking
        when(repo.findById(12)).thenReturn(Optional.of(doctor1));
        // testing
        assertEquals("Vinay", service.readById(12)
        .get().getDoctorName());
    }
    @Test
    void testFindById2(){
        // mocking
        when(repo.findById(12)).thenReturn(Optional.of(doctor1));
        // testing
        assertNotEquals("Vijay", service.readById(12)
        .get().getDoctorName());
    }

    @Test
    void testFindAllByExperienceAbove(){
        doctor1 =new Doctor();
        doctor1.setDoctorId(12);
        doctor1.setDoctorName("Richard");
        doctor1.setDoctorDepartment("Physiyo");
        doctor1.setDoctorExperience(10);
        doctor2 =new Doctor();
        doctor2.setDoctorId(88);
        doctor2.setDoctorName("Vijay");
        doctor2.setDoctorDepartment("Chemistry");
        doctor2.setDoctorExperience(9);

        myList = Stream.of(doctor1,doctor2).
        collect(Collectors.toList());

        // mock
        when(repo.findAllByExperienceAbove(9)).thenReturn(myList);
        assertTrue(service.readAboveExpeerience(9).size()<=10);
    }
}

