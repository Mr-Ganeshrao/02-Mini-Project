package in.ganesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ganesh.entity.CitiesMasterEntity;
import in.ganesh.entity.CountryMasterEntity;

@Repository
public interface CityRepository extends JpaRepository<CitiesMasterEntity, Integer> {
	
	//select * from cities_master where state_id=?
	public List<CitiesMasterEntity> findBystateId(Integer stateId);
}
