
package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiciosCliente {
       @Autowired
     private RepositorioCliente metodosCrud1;
     
     public List<Cliente> getAll(){
        return metodosCrud1.getAll();
    }
     
      public Optional<Cliente> getCliente(int clientId) {
        return metodosCrud1.getCliente(clientId);
    }

    public Cliente save(Cliente client){
        if(client.getIdClient()==null){
            return metodosCrud1.save(client);
        }else{
            Optional<Cliente> e= metodosCrud1.getCliente(client.getIdClient());
            if(e.isEmpty()){
                return metodosCrud1.save(client);
            }else{
                return client;
            }
        }
    }

    public Cliente update(Cliente client){
        if(client.getIdClient()!=null){
            Optional<Cliente> e= metodosCrud1.getCliente(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                metodosCrud1.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int id) {
        Boolean aBoolean = getCliente(id).map(client -> {
            metodosCrud1.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
