package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiciosEspecialidad {
     @Autowired
    private RepositorioEspecialidad metodosCrud;
    private int especialidadId;

    public List<Especialidad> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Especialidad> getEspecialidad(int EspecialidadId) {
        return metodosCrud.getEspecialidad(EspecialidadId);
    }

    public Especialidad save(Especialidad especialidad) {
        if (especialidad.getId()== null) {
            return metodosCrud.save(especialidad);
        } else {
            Optional<Especialidad> especialidad1 = metodosCrud.getEspecialidad(especialidad.getId());
            if (especialidad1.isEmpty()) {
                return metodosCrud.save(especialidad);
            } else {
                return especialidad;
            }
        }
    }

    public Especialidad update(Especialidad especialidad){
        if(especialidad.getId()!=null){
            Optional<Especialidad>g=metodosCrud.getEspecialidad(especialidad.getId());
            if(!g.isEmpty()){
                if(especialidad.getDescription()!=null){
                    g.get().setDescription(especialidad.getDescription());
                }
                if(especialidad.getName()!=null){
                    g.get().setName(especialidad.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return especialidad;
    }
    public boolean deletecategoria(int categoriaId){
        Boolean d=getEspecialidad(especialidadId).map(especialidad -> {
            metodosCrud.delete(especialidad);
            return true;
        }).orElse(false);
        return d;
    }
    
}
