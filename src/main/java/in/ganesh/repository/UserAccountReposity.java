package in.ganesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ganesh.entity.UserMasterEntity;

@Repository
public interface UserAccountReposity extends JpaRepository<UserMasterEntity, Integer> {

	//select * from User_account where user_email=? and user_pwd=?
	public UserMasterEntity findByEmailAndPassword(String email, String pwd);
	
	//select * from User_account where user_email=?
	public  UserMasterEntity  findByEmail(String email);

	
}
