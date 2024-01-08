package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class ListaDTO {

    private Long id;

    private String nombre;

    private String presidente;

    private String img_presidente;

}
