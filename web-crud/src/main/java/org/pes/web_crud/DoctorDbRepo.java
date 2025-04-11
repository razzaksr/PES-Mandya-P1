package org.pes.web_crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDbRepo extends JpaRepository<Doctor,Integer> {
    
}
