package org.pes.web_crud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// POJO 
@Entity
@Table(name = "surgeon")
public class Doctor {
    // @Min(value = 1, message = "{err.id}")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "surgeon_id")
    private int doctorId;
    @NotNull(message = "{err.name.empty}")
    @Pattern(regexp = "^[a-zA-Z ]{3,}", message = "{err.name}")
    @Column(name = "surgeon_name")
    private String doctorName;
    @NotNull(message = "{err.dept.empty}")
    @Pattern(regexp = "^[a-zA-Z ]{10,}", message = "{err.dept}")
    @Column(name = "surgeon_department")
    private String doctorDepartment;
    @Min(value = 1, message = "{err.exp}")
    @Column(name = "surgeon_experience")
    private int doctorExperience;
    public Doctor() {
    }
    @Override
    public String toString() {
        return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorDepartment=" + doctorDepartment
                + ", doctorExperience=" + doctorExperience + "]";
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getDoctorDepartment() {
        return doctorDepartment;
    }
    public void setDoctorDepartment(String doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }
    public int getDoctorExperience() {
        return doctorExperience;
    }
    public void setDoctorExperience(int doctorExperience) {
        this.doctorExperience = doctorExperience;
    }
}
