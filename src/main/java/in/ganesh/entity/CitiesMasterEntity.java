package in.ganesh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CITIES_MASTER")
public class CitiesMasterEntity {
	
	
	@Id
	@Column(name="CITY_ID")
	private Integer cityID;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	 

}
