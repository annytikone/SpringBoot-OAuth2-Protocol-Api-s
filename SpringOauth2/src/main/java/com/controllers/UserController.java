package com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DaoUser;
import com.model.User;
import com.repository.*;

@RestController
public class UserController {

	@Autowired
	DaoUser dao;

	@Autowired(required = true)
	UserRepository userRepository;

	@RequestMapping("/")
	public String welcome() {
		return "Hello welcome to BNT Soft !!!";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("api/userById/{id}")
	public User singleInfo(@PathVariable("id") int id) {
		/*
		 * List<User> User = dao.singleData(id); return User;
		 */
		return userRepository.findById(id);
	}

	@CrossOrigin(origins = "http://localhost:61458")
	@RequestMapping("api/getAllUserInfo")
	public List<User> getAllInfo() {
		return userRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("api/userByUsername/{username}")
	public List<User> singleInfoByUsername(@PathVariable("username") String username) {
		return (List<User>) userRepository.findByUserName(username);
	}

	@RequestMapping("signUp/{username}/{password}/{firstname}/{lastname}")
	public User signUp(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
		User u = new User();
		// b.setId(id);
		String role = "USER";
		u.setUsername(username);
		u.setPassword(password);
		u.setFirstname(firstname);
		u.setLastname(lastname);
		u.setRole("USER");
		/*
		 * if (dao.saveUser(u) == 1) { return "Successfully added to db"; } return
		 * "failed to add";
		 */

		return userRepository.save(u);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("api/del/user/{username}")
	public String deleteUserByName(@PathVariable("username") String username) {

		/*
		 * if (dao.deleteUserByName(username) == 1) { return "deleted successfully"; }
		 * 
		 * return "no record found";
		 */
		if (userRepository.deleteByUsername(username) == 0)
			return "Failed to Delete";
		else
			return "deleted";
	}

	/*
	 * // @PreAuthorize("hasAuthority('USER')")
	 * 
	 * @RequestMapping("/api/welcome")
	 * 
	 * @ResponseBody public String home() { return "Welcome home!"; }
	 * 
	 * @RequestMapping("/api/hello") public ResponseEntity<String> sayhello() {
	 * return ResponseEntity.ok("hello"); }
	 * 
	 * @RequestMapping("/user") public String p(Principal principal) { return
	 * "hello " + principal.getName(); }
	 * 
	 * @PreAuthorize("hasAuthority('ADMIN')")
	 * 
	 * @RequestMapping("/api/user")
	 * 
	 * @ResponseBody public String userAthority(Principal principal) { return
	 * "Welcome home ADMIN !" + principal.getName(); }
	 * 
	 * @RequestMapping("/api/logout")
	 * 
	 * @ResponseBody public String userLogout() { return
	 * "visit again..Logged out !"; }
	 */

}
