package in.ganesh.binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data 
public class UserForm {
	

	private String fname;
	
	private String lname;
	
	private String email;
	
	private Long phno;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate dob;
	
	private String gender;
	
	private Integer cityId;

	private Integer stateId;
	
	private Integer countryId;
	
	

}
