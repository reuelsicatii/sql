package org.nimphus.sql.repository;

import org.nimphus.sql.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UserContact, Long> {

}
