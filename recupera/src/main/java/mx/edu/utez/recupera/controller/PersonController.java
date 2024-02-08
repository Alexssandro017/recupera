package mx.edu.utez.recupera.controller;

import lombok.AllArgsConstructor;
import mx.edu.utez.recupera.model.dto.PersonDto;
import mx.edu.utez.recupera.model.entity.PersonBean;
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
public class PersonController {

    private final PersonImpl personService;

    @PostMapping("/user")
    public PersonDto create(@RequestBody PersonDto personDto){
        PersonBean personSave = personService.save(personDto);
        return personDto.builder()
                .id(personSave.getId())
                .username(personDto.getUsername())
                .nombre(personSave.getNombre())
                .apellido_paterno(personSave.getApellido_paterno())
                .apellido_materno(personSave.getApellido_materno())
                .fecha_nacimiento(personSave.getFecha_nacimiento())
                .lugar_nacimiento(personSave.getLugar_nacimiento())
                .sexo(personSave.getSexo())
                .password(personSave.getPassword())
                .direccion(personSave.getDireccion())
                .activo(personSave.isActivo())
                .build();
    }

    @PutMapping("/user")
    public PersonDto update(@RequestBody PersonDto personDto){
        PersonBean personUpdate = personService.save(personDto);
        return personDto.builder()
                .id(personUpdate.getId())
                .username(personUpdate.getUsername())
                .nombre(personUpdate.getNombre())
                .apellido_paterno(personUpdate.getApellido_paterno())
                .apellido_materno(personUpdate.getApellido_materno())
                .fecha_nacimiento(personUpdate.getFecha_nacimiento())
                .lugar_nacimiento(personUpdate.getLugar_nacimiento())
                .sexo(personUpdate.getSexo())
                .password(personUpdate.getPassword())
                .direccion(personUpdate.getDireccion())
                .activo(personUpdate.isActivo())
                .build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            PersonBean person = personService.findById(id);
            personService.delete(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (DataAccessException ex){
            response.put("mensaje", ex.getMessage());
            response.put("Person", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public PersonBean showById(@PathVariable Integer id){
        try{
            return personService.findById(id);
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en Base de Datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }
    }

    @GetMapping("/user")
    public List<PersonBean> findAll(){
        try{
            return personService.findAll();
        } catch (DataAccessException dae){
            throw new RuntimeException("Error en la base de datos: ", dae);
        } catch (Exception ex){
            throw new RuntimeException("Error al obtener la persona: ", ex);
        }

    }
}
