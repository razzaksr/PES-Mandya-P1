package org.pes.web_crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HRServices implements UserDetailsService{
    @Autowired
    private HRRepository repository;

    public HR create(HR hr){
        return repository.save(hr);
    }
    public HR readById(int id){
        return repository.findById(id).orElse(null);
    }
    public List<HR> readAll(){
        return repository.findAll();
    }
    public void deleteViaId(int id){
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HR hr = repository.findByUsername(username);
        if(hr==null)
            throw new UsernameNotFoundException(username);
        return hr;
    }
}
