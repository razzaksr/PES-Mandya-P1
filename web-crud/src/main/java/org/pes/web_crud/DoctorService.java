package org.pes.web_crud;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DoctorService implements DoctorRepo{

    // permenant storage
    File file=new File("hospital.json");
    // temporary storage
    List<Doctor> temporaryDoctors = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Doctor> readAll() {
        try {
            // load values from file
            if(!file.exists())
                temporaryDoctors = new ArrayList<>();
            temporaryDoctors = objectMapper.readValue
            (file, objectMapper.getTypeFactory().
            constructCollectionType
            (List.class,Doctor.class));
        }
        catch(Exception ex){}
        return temporaryDoctors;
    }

    @Override
    public String insert(Doctor doctor) {
        try {
            // load values from file
            if(!file.exists()){
                file.createNewFile();
                objectMapper.writeValue(file, temporaryDoctors);
                temporaryDoctors = new ArrayList<>();
            }
            temporaryDoctors = objectMapper.readValue
            (file, objectMapper.getTypeFactory().
            constructCollectionType
            (List.class,Doctor.class));

            // add new doctor
            temporaryDoctors.add(doctor);

            // replace/write
            objectMapper.writeValue(file, temporaryDoctors);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doctor.getDoctorName()+" recruited";
    }

    @Override
    public String terminate(int doctorId) {
        temporaryDoctors = readAll();
        boolean status = temporaryDoctors.
        removeIf(doc->doc.getDoctorId()==doctorId);
        try {
            objectMapper.writeValue(file, temporaryDoctors);
        } catch (StreamWriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status?"terminated":"failed to terminate";
    }

    @Override
    public String update(Doctor doctor) {
        temporaryDoctors = readAll();
        for(int index=0;index<temporaryDoctors.size();index++){
            if(temporaryDoctors.get(index).getDoctorId()==doctor.getDoctorId()){
                // replace
                temporaryDoctors.set(index, doctor);
                try {
                    objectMapper.writeValue(file, temporaryDoctors);
                } catch (StreamWriteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (DatabindException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "updated";
            }
        }
        return "Not updated";
    }
    
}
