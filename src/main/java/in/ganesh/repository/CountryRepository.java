package in.ganesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ganesh.entity.CountryMasterEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
