package ec.voto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.voto.api.domain.Estudiante;

public interface EstudiantePersistence extends JpaRepository<Estudiante, Long> {
}
