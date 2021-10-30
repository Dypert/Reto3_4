package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiciosEspecialidad {
     @Autowired
    private RepositorioEspecialidad metodosCrud2;
   
    
 public List<Especialidad> getAll() {
        return metodosCrud2.getAll();
    }
     
public Optional<Especialidad> getSpecialty(int EspecialidadId) {
        return metodosCrud2.getSpecialty(EspecialidadId);
    }

    public Especialidad save(Especialidad especialidad) {
        if (especialidad.getId()== null) {
            return metodosCrud2.save(especialidad);
        } else {
            Optional<Especialidad> especialidad1 = metodosCrud2.getSpecialty(especialidad.getId());
            if (especialidad1.isEmpty()) {
                return metodosCrud2.save(especialidad);
            } else {
                return especialidad;
            }
        }
    }

    public Especialidad update(Especialidad especialidad){
        if(especialidad.getId()!=null){
            Optional<Especialidad>g=metodosCrud2.getSpecialty(especialidad.getId());
            if(!g.isEmpty()){
                if(especialidad.getName()!=null){
                    g.get().setName(especialidad.getName());
                }
                if(especialidad.getDescription()!=null){
                    g.get().setDescription(especialidad.getDescription());
                }
                return metodosCrud2.save(g.get());
            }
        }
        return especialidad;
        
      
    }
    public boolean deleteEspecialidad(int especialidadId){
        Boolean d=getSpecialty(especialidadId).map(especialidad -> {
            metodosCrud2.delete(especialidad);
            return true;
        }).orElse(false);
        return d;
    }

   
    
}
