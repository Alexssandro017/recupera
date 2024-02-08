package mx.edu.utez.recupera.controller;

import lombok.AllArgsConstructor;
import mx.edu.utez.recupera.model.dto.ClienteDto;
import mx.edu.utez.recupera.model.dto.PersonDto;
import mx.edu.utez.recupera.model.entity.ClienteBean;
import mx.edu.utez.recupera.model.entity.PersonBean;
import mx.edu.utez.recupera.service.impl.ClienteImpl;
import mx.edu.utez.recupera.service.impl.PersonImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recupera")
public class ClienteController {

    private final ClienteImpl clienteService;

    @PostMapping("/cliente")
    public ClienteDto create(@RequestBody ClienteDto clienteDto){
        ClienteBean clienteSave = clienteService.save(clienteDto);
        return clienteDto.builder()
                .id(clienteSave.getId())
                .nombre(clienteSave.getNombre())
                .apellido_paterno(clienteSave.getApellido_paterno())
                .apellido_materno(clienteSave.getApellido_materno())
                .fecha_nacimiento(clienteSave.getFecha_nacimiento())
                .lugar_nacimiento(clienteSave.getLugar_nacimiento())
                .sexo(clienteSave.getSexo())
                .direccion(clienteSave.getDireccion())
                .activo(clienteSave.isActivo())
                .build();
    }

    @PutMapping("/cliente")
    public ClienteDto update(@RequestBody ClienteDto clienteDto){
        ClienteBean clienteUpdate = clienteService.save(clienteDto);
        return clienteDto.builder()
                .id(clienteUpdate.getId())
                .nombre(clienteUpdate.getNombre())
                .apellido_paterno(clienteUpdate.getApellido_paterno())
                .apellido_materno(clienteUpdate.getApellido_materno())
                .fecha_nacimiento(clienteUpdate.getFecha_nacimiento())
                .lugar_nacimiento(clienteUpdate.getLugar_nacimiento())
                .sexo(clienteUpdate.getSexo())
                .direccion(clienteUpdate.getDireccion())
                .activo(clienteUpdate.isActivo())
                .build();
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            ClienteBean cliente = clienteService.findById(id);
            clienteService.delete(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (DataAccessException ex){
            response.put("mensaje", ex.getMessage());
            response.put("Person", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    public ClienteBean showById(@PathVariable Integer id){
        try{
            return clienteService.findById(id);
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en Base de Datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }
    }

    @GetMapping("/cliente")
    public List<ClienteBean> findAll(){
        try{
            return clienteService.findAll();
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en la base de datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }
    }
}
