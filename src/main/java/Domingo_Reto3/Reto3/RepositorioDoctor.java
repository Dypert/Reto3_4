package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioDoctor {
    
    @Autowired
    private InterfaceDoctor crudDoctor;

    public List<Doctor> getAll(){
        return (List<Doctor>) crudDoctor.findAll();
    }

    public Optional<Doctor> getDoctor(int id){
        return crudDoctor.findById(id);
    }

    public Doctor save(Doctor doctor){
        return crudDoctor.save(doctor);
    }
    public void delete(Doctor doctor){
        crudDoctor.delete(doctor);
    }
/**
 * 
 * @param doctorId
 * @return 
 */
   
    
}
