package ec.voto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.voto.api.domain.Curso;

public interface CursoPersistence extends JpaRepository<Curso, Long> {
}
