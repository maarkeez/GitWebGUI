package com.gitwebgui.repository;

import com.gitwebgui.model.persistence.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
