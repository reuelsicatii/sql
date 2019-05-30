package org.nimphus.sql.controller;

import java.util.List;

import org.nimphus.sql.model.User;
import org.nimphus.sql.model.UserContact;
import org.nimphus.sql.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usercontact")
public class UserContactController {

	UserContactRepository userContactRepository;

	@Autowired
	public UserContactController(UserContactRepository userContactRepository) {
		super();
		this.userContactRepository = userContactRepository;
	}

	@GetMapping(value = "/all")
	public List<UserContact> findAll() {
		return userContactRepository.findAll();
	}	

	@PostMapping(value = "/addUserContact")
	public UserContact addUserContact(@RequestBody final UserContact UserContact) {

		userContactRepository.save(UserContact);
		return UserContact;
	}
	
	@PostMapping(value = "/addUserContacts")
	public List<UserContact> addUserContacts(@RequestBody final List<UserContact> UserContacts) {

		for (UserContact userContact : UserContacts) {
			userContactRepository.save(userContact);
		}

		return UserContacts;
	}
	
	// Needs to be resolved - RESOLVED
	// =================================================================================
	// "could not execute statement; nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement"
	// **********************************************************************************************************
	// Resolution: Database. Set ID to IDENTITY. Clean/Drop DB
	@PostMapping(value = "/addUserwithUserContact")
	public List<UserContact> addUserwithUserContact(@RequestBody final List<UserContact> UserwithContacts) {

		for (UserContact UserwithContact : UserwithContacts) {
			userContactRepository.save(UserwithContact);
		}
		
		return userContactRepository.findAll();
	}

}
