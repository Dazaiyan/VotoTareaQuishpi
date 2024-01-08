package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class EstudianteDTO {

    private Long id;

    private String nombre;

    private String cedula;

    private String asistencia;

    private CursoDTO curso;

    private VotoDTO voto;

}
