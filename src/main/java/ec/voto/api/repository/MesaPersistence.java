package ec.voto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.voto.api.domain.Mesa;

public interface MesaPersistence extends JpaRepository<Mesa, Long> {

}
