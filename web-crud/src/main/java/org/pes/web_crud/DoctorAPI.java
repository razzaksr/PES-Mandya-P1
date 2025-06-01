package org.pes.web_crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('manager')")
    @PostMapping("/specs")
    public ResponseEntity<Page<Doctor>> getProducts(
        @RequestBody SearchRequest searchRequest) {

        Sort sort = searchRequest.getSortDir().equalsIgnoreCase("asc") 
                    ? Sort.by(searchRequest.getSortBy()).ascending() 
                    : Sort.by(searchRequest.getSortBy()).descending();

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);
        Page<Doctor> products = doctorService.getFilteredProducts(searchRequest.getFilters(), pageable);
        return ResponseEntity.ok(products);

    }

    @PreAuthorize("hasAuthority('manager')")
    @PutMapping("/promote/{exp}")
    public ResponseEntity<Void> callChange(@PathVariable int exp){
        doctorService.changeByExperience(exp);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyAuthority('manager','user')")
    @GetMapping("/person/{name}")
    public ResponseEntity<List<String>> callNames(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readDepartments(name));
    }
    @PreAuthorize("hasAnyAuthority('manager','user')")
    @GetMapping("/work/{role}")
    public ResponseEntity<List<String>> callDepartment(@PathVariable String role){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readNames(role));
    }
    @PreAuthorize("hasAnyAuthority('manager','user')")
    @GetMapping("/max/{exp}")
    public ResponseEntity<List<Doctor>> callGreater(@PathVariable int exp){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAboveExpeerience(exp));
    }
    @PreAuthorize("hasAnyAuthority('manager','user')")
    @GetMapping("/filter/{exp}")
    public ResponseEntity<List<Doctor>> callByExperience(@PathVariable int exp){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAllExperience(exp));
    }
    @PreAuthorize("hasAnyAuthority('manager','user')")
    @GetMapping("/{surgeon}")
    public ResponseEntity<Doctor> callReadId(@PathVariable int surgeon){
        Optional<Doctor> received = doctorService.readById(surgeon);
        return received.map(ResponseEntity::ok)
        .orElseGet(()->ResponseEntity.
        status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasAnyAuthority('manager','admin')")
    @PutMapping("/replace")
    public ResponseEntity<String> callUpdate(@RequestBody Doctor doctor){
        String res = doctorService.update(doctor);
        return res.equals("updated")?
        ResponseEntity.status(HttpStatus.CREATED).body(res)
        :ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
    @PreAuthorize("hasAnyAuthority('manager','admin')")
    @DeleteMapping("/erase/{code}")
    public ResponseEntity<String> callDelete(@PathVariable int code){
        String msg = doctorService.terminate(code);
        return msg.equals("terminated")?ResponseEntity.
        status(HttpStatus.OK).body(msg):ResponseEntity.
        status(HttpStatus.NOT_FOUND).body(msg);
    }
    @PreAuthorize("hasAnyAuthority('manager','user','admin')")
    @GetMapping("/view")
    public ResponseEntity<List<Doctor>> callReadAll(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.readAll());
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/new")
    public ResponseEntity<String> callInsert(@Valid @RequestBody Doctor doctor){
        String res = doctorService.insert(doctor);
        return res.equals(doctor.getDoctorName()+" recruited")?
        ResponseEntity.status(HttpStatus.CREATED).body(res)
        :ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
