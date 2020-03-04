package com.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.model.book;



@Repository
public interface UserBooksRepository extends JpaRepository<book, Integer> {
	
	List<book> findBybookname(String bookname);

	@Transactional
	@Modifying
	@Query(value=" delete from book where bookname= ?1",nativeQuery = true)
	int deleteBookByName(String bookname);

}
