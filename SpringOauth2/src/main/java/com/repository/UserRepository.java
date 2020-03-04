package com.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.User;
import com.model.book;

//implements userdetails service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {// CrudRepository<User,Long>{

	User findByUsername(String userName);

	User findById(int id);

	@Query(value = "select * from user where username= ?1", nativeQuery = true)
	List<User> findByUserName(String userName);

	@Query(value = "select * from user", nativeQuery = true)
	List<User> gettAllUsers();

	@Transactional
	@Modifying
	@Query(value = " delete from user where username= ?1", nativeQuery = true)
	int deleteByUsername(String username);

}
