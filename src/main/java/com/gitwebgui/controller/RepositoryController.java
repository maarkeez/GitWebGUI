package com.gitwebgui.controller;

import com.gitwebgui.model.exception.AppException;
import com.gitwebgui.model.persistence.Repository;
import com.gitwebgui.model.persistence.User;
import com.gitwebgui.repository.RepositoryRepository;
import com.gitwebgui.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("api/git/")
public class RepositoryController {

    public static final Path DEFAULT_BASE_PATH = Paths.get(System.getProperty("user.home")).resolve("git-web-gui");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @PostMapping("user/{userId}/repository/create")
    public @ResponseBody
    Repository onCreate(@PathVariable("userId") Long userId, @RequestBody Repository repository) throws AppException, GitAPIException {

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new AppException("User not found for identifier: " + userId);
        }

        if(StringUtils.isBlank(repository.getName())){
            throw new AppException("Required field: repository-name");
        }

        Path path;
        if(StringUtils.isBlank(repository.getPath())){
            path = DEFAULT_BASE_PATH.resolve(repository.getName());
        }else{
            path = Paths.get(repository.getPath());
        }

        if(path.toFile().exists()){
            throw new AppException("Repository path should not exists. Path:'"+path.toAbsolutePath().toString()+"'");
        }

        Git.init().setDirectory(path.toFile()).call();

        repository.setPath(path.toAbsolutePath().toString());
        repository.setUser(user.get());
        return repositoryRepository.save(repository);
    }
}
