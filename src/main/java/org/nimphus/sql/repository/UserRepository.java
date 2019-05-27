package org.nimphus.sql.repository;

import java.util.List;

import org.nimphus.sql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);

	User findByDepartment(String department);

}
