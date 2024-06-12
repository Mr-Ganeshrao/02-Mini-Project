package in.ganesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ganesh.entity.StateMasterEntity;

@Repository
public interface StateRepository extends JpaRepository<StateMasterEntity, Integer> {

	//select * from state_master where country_id=?
	public List<StateMasterEntity> findBycountryId(Integer countryId);
}
