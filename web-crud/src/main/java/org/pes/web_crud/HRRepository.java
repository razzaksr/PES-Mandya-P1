package org.pes.web_crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRRepository extends JpaRepository<HR,Integer>{
    HR findByUsername(String username);
}
