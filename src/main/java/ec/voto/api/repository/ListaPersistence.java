package ec.voto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.voto.api.domain.Lista;

public interface ListaPersistence extends JpaRepository<Lista, Long> {
}
