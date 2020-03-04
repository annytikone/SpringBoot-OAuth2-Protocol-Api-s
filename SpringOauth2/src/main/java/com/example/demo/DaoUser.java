package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.model.User;



@Repository
public class DaoUser {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select * from user";

	private static final String SQL2 = "select * from user where id=";

	private static final String SQL3 = " select * from user where username=";

	private static final String SQL4 = " delete from user where username=";

	public static List<User> getGenericResult(List<Map<String, Object>> rows) {
		List<User> Users = new ArrayList<User>();

		for (Map<String, Object> row : rows) {
			User User = new User();
			User.setId((int) row.get("id"));
			User.setUsername((String) row.get("Username"));
			User.setPassword((String) row.get("password"));
			User.setFirstname((String) row.get("firstname"));
			User.setLastname((String) row.get("lastname"));
			User.setRole((String) row.get("role"));
			Users.add(User);
		}
		return Users;

	}

	public List<User> isData() {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

		return getGenericResult(rows);
	}

	public List<User> singleData(long id) {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL2 + id);

		return getGenericResult(rows);
	}

	public List<User> getUserByName(String Username) {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL3 + "'" + Username + "'");
		return getGenericResult(rows);

	}

	public int deleteUserByName(String Username) {
		String query = SQL4 + "'" + Username + "'";

		return jdbcTemplate.update(query);
	}

	public int saveUser(User s) {
		String query = "insert into user values('" + s.getId() + "','" + s.getUsername() + "','" + s.getPassword()
				+ "','" + s.getFirstname() + "','" + s.getLastname() +"','"+s.getRole()+ "')";

		return jdbcTemplate.update(query);
	}
}
