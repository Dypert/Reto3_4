package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RepositorioEspecialidad {
    
    @Autowired
    private InterfaceEspecialidad crud;
    public List<Especialidad> getAll(){
        return (List<Especialidad>) crud.findAll();
    }
    public Optional<Especialidad> getCategoria(int id){
        return crud.findById(id);
    }

    public Especialidad save(Especialidad Especialidad){
        return crud.save(Especialidad);
    }
    public void delete(Especialidad Especialidad){
       crud.delete(Especialidad);
    }

    Optional<Especialidad> getEspecialidad(int EspecialidadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
