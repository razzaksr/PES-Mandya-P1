package org.pes.web_crud;

import java.util.List;
import java.util.Optional;

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
    DoctorDbService doctorService;
    // DoctorService doctorService;

    @PutMapping("/promote/{exp}")
    public ResponseEntity<Void> callChange(@PathVariable int exp){
        doctorService.changeByExperience(exp);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/person/{name}")
    public ResponseEntity<List<String>> callNames(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readDepartments(name));
    }

    @GetMapping("/work/{role}")
    public ResponseEntity<List<String>> callDepartment(@PathVariable String role){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readNames(role));
    }

    @GetMapping("/max/{exp}")
    public ResponseEntity<List<Doctor>> callGreater(@PathVariable int exp){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAboveExpeerience(exp));
    }

    @GetMapping("/filter/{exp}")
    public ResponseEntity<List<Doctor>> callByExperience(@PathVariable int exp){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAllExperience(exp));
    }

    @GetMapping("/{surgeon}")
    public ResponseEntity<Doctor> callReadId(@PathVariable int surgeon){
        Optional<Doctor> received = doctorService.readById(surgeon);
        return received.map(ResponseEntity::ok)
        .orElseGet(()->ResponseEntity.
        status(HttpStatus.NOT_FOUND).build());
    }


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
