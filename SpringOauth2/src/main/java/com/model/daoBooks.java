package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class daoBooks {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select * from book";

	private static final String SQL2 = "select * from book where id=";

	private static final String SQL3 = " select * from book where bookname=";

	private static final String SQL4 = " delete from book where bookname=";

	public static List<book> getGenericResult(List<Map<String, Object>> rows) {
		List<book> books = new ArrayList<book>();

		for (Map<String, Object> row : rows) {
			book book = new book();
			book.setId((int) row.get("id"));
			book.setBookname((String) row.get("bookname"));
			book.setBooktype((String) row.get("booktype"));
			book.setAuthorname((String) row.get("authorname"));
			book.setPrice((Double) row.get("price"));
			books.add(book);
		}
		return books;

	}

	public List<book> isData() {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

		return getGenericResult(rows);
	}

	public List<book> singleData(long id) {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL2 + id);

		return getGenericResult(rows);
	}

	public List<book> getBookByName(String bookname) {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL3 + "'" + bookname + "'");
		return getGenericResult(rows);

	}

	public int deleteBookByName(String bookname) {
		String query = SQL4 + "'" + bookname + "'";

		return jdbcTemplate.update(query);
	}

	public int savebook(book s) {
		String query = "insert into book values('" + s.getId() + "','" + s.getBookname() + "','" + s.getBooktype()
				+ "','" + s.getAuthorname() + "','" + s.getPrice() + "')";

		return jdbcTemplate.update(query);
	}

}
