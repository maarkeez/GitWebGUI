package com.gitwebgui.controller;

import com.gitwebgui.model.exception.AppException;
import com.gitwebgui.model.persistence.User;
import com.gitwebgui.model.request.CloneRequest;
import com.gitwebgui.model.response.CloneResponse;
import com.gitwebgui.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("api/git/")
public class GitController {

    private Optional<CredentialsProvider> credentialsProvider = Optional.empty();

    @Autowired
    private UserRepository userRepository;

    /**
     * maarkeez 5ae040bd094ad836d9337e8cae65a2b1034c45fe
     *
     * @param request
     * @return
     */
    @PostMapping("login")
    public @ResponseBody
    User onLogin(@RequestBody User request) {

        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user = userRepository.save(user);

        credentialsProvider = Optional.of(new UsernamePasswordCredentialsProvider(user.getName(), user.getPassword()));
        return user;
    }

    @PostMapping("clone")
    public @ResponseBody
    CloneResponse onClone(@RequestBody CloneRequest request) throws GitAPIException, IOException, AppException {

        if(StringUtils.isBlank(request.getRepositoryUrl())){
            throw new AppException("Required field: repository-url");
        }

        Path path;
        if (StringUtils.isBlank(request.getPath())) {
            path = Files.createTempDirectory("git").toAbsolutePath();
            request.setPath(path.toString());
        } else {
            path = Paths.get(request.getPath());
        }

        CloneCommand cloneCommand = Git.cloneRepository().setDirectory(path.toFile());
        cloneCommand.setURI(request.getRepositoryUrl());
        // cloneCommand.setCredentialsProvider(credentialsProvider);
        cloneCommand.call();

        return new CloneResponse(request.getPath());
    }
}
