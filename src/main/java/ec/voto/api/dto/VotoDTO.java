package ec.voto.api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class VotoDTO {

    private Long id;

    private ListaDTO lista;

    private MesaDTO mesa;

}
