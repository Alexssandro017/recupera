package mx.edu.utez.recupera.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Integer id;
    private String username;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String fecha_nacimiento;
    private String lugar_nacimiento;
    private String sexo;
    private String password;
    private String direccion;
    private boolean activo;

}
