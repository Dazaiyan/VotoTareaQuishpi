package ec.voto.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ec.voto.api.common.ApiException;

@Service
public abstract class GenericCrudServiceImpl<DOMAIN, DTO> implements GenericCrudService<DOMAIN, DTO> {

	@Autowired
	private JpaRepository<DOMAIN, Long> repository;

	@Override
	public DTO save(DTO dto) {
		Optional<DOMAIN> optional = find(dto);
		DOMAIN domainObjectResult = null;
		if (optional.isEmpty()) {
			DOMAIN domainObject = mapToDomain(dto);
			try {
				domainObjectResult = repository.save(domainObject);
			} catch (Exception e) {
				throw new ApiException(String.format(e.getMessage()));
			}
		} else {
			throw new ApiException(String.format("Registro ya existe en el sistema"));
		}
		return mapToDto(domainObjectResult);
	}

	@Override
	public DTO update(DTO dto) {
		Optional<DOMAIN> optional = find(dto);
		DOMAIN domainObjectResult = null;
		if (optional.isEmpty()) {
			throw new ApiException(String.format("Registro no existe en el sistema"));
		} else {
			DOMAIN domainObject = mapToDomain(dto);
			try {
				domainObjectResult = repository.save(domainObject);
			} catch (Exception e) {
				throw new ApiException(String.format(e.getMessage()));
			}
		}
		return mapToDto(domainObjectResult);
	}

	@Override
	public void delete(DTO dto) {
		Optional<DOMAIN> optional = find(dto);
		if (!optional.isPresent()) {
			throw new ApiException(String.format("Registro no existe en el sistema"));
		} else {
			DOMAIN domainObject = mapToDomain(dto);
			try {
				repository.delete(domainObject);
			} catch (Exception e) {
				throw new ApiException(String.format(e.getMessage()));
			}
		}
	}

	@Override
	public List<DTO> findAll(DTO dto) {
		DOMAIN domain = mapToDomain(dto);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		List<DOMAIN> objList = repository.findAll(Example.of(domain, matcher));
		return objList.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
	}

	@Override
	public abstract DOMAIN mapToDomain(DTO dto);

	@Override
	public abstract DTO mapToDto(DOMAIN domain);
}
