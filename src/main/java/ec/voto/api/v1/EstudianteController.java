package ec.voto.api.v1;

import java.util.List;
import java.util.Optional;

import ec.voto.api.dto.EstudianteDTO;
import ec.voto.api.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.voto.api.dto.ApiResponseDTO;

import ec.voto.api.service.EstudianteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/api/v1.0/estudiante" })
public class EstudianteController {
    @Autowired
    EstudianteService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar() {
        List<EstudianteDTO> list = service.findAll(new EstudianteDTO());
        ApiResponseDTO<List<EstudianteDTO>> response = new ApiResponseDTO<>(true, list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardar(@RequestBody EstudianteDTO EstudianteDTO) {
        EstudianteDTO EstudianteDTOResult = service.save(EstudianteDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, EstudianteDTOResult), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody EstudianteDTO EstudianteDTO) {
        EstudianteDTO resultDTO = service.update(EstudianteDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        // Verificar si la mesa con el ID proporcionado existe antes de intentar eliminar
        Optional<EstudianteDTO> estudianteDTO = service.findById(id);
        if (estudianteDTO.isPresent()) {
            service.deleteById(id);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Mesa eliminada correctamente"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "No se encontró la mesa con el ID proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
    }
}