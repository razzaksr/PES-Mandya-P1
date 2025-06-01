package org.pes.web_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr")
public class HRApi {
    @Autowired
    private HRServices services;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/")
    public HR saving(@RequestBody HR hr){
        hr.setPassword(encoder.encode(hr.getPassword()));
        return services.create(hr);
    }
}
