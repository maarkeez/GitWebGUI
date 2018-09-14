package com.gitwebgui.repository;

import com.gitwebgui.model.persistence.Repository;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryRepository extends CrudRepository<Repository,Long> {
    
}
