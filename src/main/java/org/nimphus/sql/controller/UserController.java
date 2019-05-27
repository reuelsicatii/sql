package org.nimphus.sql.controller;

import java.util.List;

import org.nimphus.sql.model.User;
import org.nimphus.sql.model.UserContact;
import org.nimphus.sql.repository.UserContactRepository;
import org.nimphus.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	UserRepository userRepository;
	UserContactRepository userContactRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@GetMapping(value = "/all")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@PostMapping(value = "/addUser")
	public List<User> addUser(@RequestBody final List<User> users) {

		for (User user : users) {
			userRepository.save(user);
		}

		return userRepository.findAll();
	}

	// Needs to be resolved - RESOLVE
	// =================================================================================
	// "JSON parse error: Cannot deserialize instance of `java.util.ArrayList` out
	// of START_OBJECT token; nested exception is
	// com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot
	// deserialize instance of `java.util.ArrayList` out of START_OBJECT token\n at
	// [Source: (PushbackInputStream); line: 1, column: 1]"
	// **********************************************************************************************************
	// Resolution: Please see UserContactController.addUserwithUserContact
	@PostMapping(value = "/addUserwithContact")
	public List<User> addUserwithContact(@RequestBody final List<User> UserswithContact) {

		User tempUser = new User();
		UserContact tempUserContact = new UserContact();
		for (User UserwithContact : UserswithContact) {

			tempUser.setName(UserwithContact.getName());
			tempUser.setDepartment(UserwithContact.getDepartment());
			tempUser.setSalary(UserwithContact.getSalary());
			System.out.println(tempUser.toString());

			tempUserContact.setAddress_1(UserwithContact.getUserContact().getAddress_1());
			tempUserContact.setAddress_2(UserwithContact.getUserContact().getAddress_2());
			tempUserContact.setTelNo(UserwithContact.getUserContact().getTelNo());
			System.out.println(tempUserContact.toString());

			tempUser.setUserContact(tempUserContact);

		}

		userRepository.save(tempUser);
		return UserswithContact;
	}

	@GetMapping(value = "/delete/{id}")
	public List<User> delete(@PathVariable("id") final Long id) {
		userRepository.deleteById(id);
		return userRepository.findAll();
	}

	@GetMapping(value = "/find/{name}")
	public List<User> getName(@PathVariable("name") final String name) {
		return userRepository.findByName(name);
	}

	// Needs to be resolved - RESOLVED (see below)
	// =================================================================================
	// Exception: Type definition error: [simple type, class
	// org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor]; nested exception is
	// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer
	// found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and
	// no properties discovered to create BeanSerializer (to avoid exception,
	// disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain:
	// java.util.ArrayList[0]->org.nimphus.sql.model.User$HibernateProxy$Z3U8Z0SI[\"hibernateLazyInitializer\"])
	// **********************************************************************************************************
	// Resolution: The problem is that entities are loaded lazily and serialization
	// happens before they get loaded fully.
	@PostMapping(value = "/update/{id}/salary")
	public List<User> updateSalary(@PathVariable("id") final Long id, @RequestBody final User user) {
		User tempUser = userRepository.getOne(id);

		tempUser.setSalary(user.getSalary());
		userRepository.save(tempUser);

		return userRepository.findAll();
	}

	// Needs to be resolved - RESOLVED (see below)
	// =================================================================================
	// Exception: Type definition error: [simple type, class
	// org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor]; nested exception is
	// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer
	// found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and
	// no properties discovered to create BeanSerializer (to avoid exception,
	// disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain:
	// java.util.ArrayList[0]->org.nimphus.sql.model.User$HibernateProxy$Z3U8Z0SI[\"hibernateLazyInitializer\"])
	// **********************************************************************************************************
	// Resolution: The problem is that entities are loaded lazily and serialization
	// happens before they get loaded fully.
	@PostMapping(value = "/update/{id}/object")
	public List<User> updatename(@PathVariable("id") final Long id, @RequestBody final User user) {
		User tempUser = userRepository.getOne(id);

		tempUser.setName(user.getName());
		tempUser.setSalary(user.getSalary());
		tempUser.setDepartment(user.getDepartment());
		userRepository.save(tempUser);

		return userRepository.findAll();
	}

}
