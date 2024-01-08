package ec.voto.api.v1;

import java.util.List;

import ec.voto.api.dto.MesaDTO;
import java.util.Optional;
import ec.voto.api.repository.MesaPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.voto.api.dto.ApiResponseDTO;

import ec.voto.api.service.MesaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/api/v1.0/mesa" })
public class MesaController {
    @Autowired
    MesaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar() {
        List<MesaDTO> list = service.findAll(new MesaDTO());
        ApiResponseDTO<List<MesaDTO>> response = new ApiResponseDTO<>(true, list);
        return (new ResponseEntity<Object>(response, HttpStatus.OK));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardar(@RequestBody MesaDTO MesaDTO) {
        MesaDTO MesaDTOResult = service.save(MesaDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, MesaDTOResult), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody MesaDTO MesaDTO) {
        MesaDTO resultDTO = service.update(MesaDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        // Verificar si la mesa con el ID proporcionado existe antes de intentar eliminar
        Optional<MesaDTO> mesaDTO = service.findById(id);
        if (mesaDTO.isPresent()) {
            service.deleteById(id);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Mesa eliminada correctamente"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "No se encontró la mesa con el ID proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
        MesaDTO dto = new MesaDTO();
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
    }
}
