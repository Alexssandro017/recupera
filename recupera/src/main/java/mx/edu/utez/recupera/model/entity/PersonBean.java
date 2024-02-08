package mx.edu.utez.recupera.model.entity;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class PersonBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellido_paterno;
    @Column(name = "apellido_materno")
    private String apellido_materno;
    @Column(name = "fecha_nacimiento")
    private String fecha_nacimiento;
    @Column(name = "lugar_nacimiento")
    private String lugar_nacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column (name = "password")
    private String password;
    @Column (name = "direccion")
    private String direccion;
    @Column (name = "estatus")
    private boolean activo;
}
