package org.pes.web_crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doc")
public class DoctorAPI {
    // dependency injection
    @Autowired
    DoctorService doctorService;

    @PutMapping("/replace")
    public ResponseEntity<String> callUpdate(@RequestBody Doctor doctor){
        String res = doctorService.update(doctor);
        return res.equals("updated")?
        ResponseEntity.status(HttpStatus.CREATED).body(res)
        :ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @DeleteMapping("/erase/{code}")
    public ResponseEntity<String> callDelete(@PathVariable int code){
        String msg = doctorService.terminate(code);
        return msg.equals("terminated")?ResponseEntity.
        status(HttpStatus.OK).body(msg):ResponseEntity.
        status(HttpStatus.NOT_FOUND).body(msg);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Doctor>> callReadAll(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAll());
    }

    @PostMapping("/new")
    public ResponseEntity<String> callInsert(@Valid @RequestBody Doctor doctor){
        String res = doctorService.insert(doctor);
        return res.equals(doctor.getDoctorName()+" recruited")?
        ResponseEntity.status(HttpStatus.CREATED).body(res)
        :ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
