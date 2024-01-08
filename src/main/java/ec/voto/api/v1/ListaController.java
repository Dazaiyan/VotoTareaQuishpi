package ec.voto.api.v1;

import java.util.List;
import java.util.Optional;

import ec.voto.api.dto.ListaDTO;
import ec.voto.api.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.voto.api.dto.ApiResponseDTO;

import ec.voto.api.service.ListaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/api/v1.0/lista" })
public class ListaController {
    @Autowired
    ListaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar() {
        List<ListaDTO> list = service.findAll(new ListaDTO());
        ApiResponseDTO<List<ListaDTO>> response = new ApiResponseDTO<>(true, list);
        return (new ResponseEntity<Object>(response, HttpStatus.OK));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardar(@RequestBody ListaDTO ListaDTO) {
        ListaDTO ListaDTOResult = service.save(ListaDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, ListaDTOResult), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody ListaDTO ListaDTO) {
        ListaDTO resultDTO = service.update(ListaDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        // Verificar si la mesa con el ID proporcionado existe antes de intentar eliminar
        Optional<ListaDTO> listaDTO = service.findById(id);
        if (listaDTO.isPresent()) {
            service.deleteById(id);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Mesa eliminada correctamente"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "No se encontró la mesa con el ID proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
        ListaDTO dto = new ListaDTO();
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
    }
}