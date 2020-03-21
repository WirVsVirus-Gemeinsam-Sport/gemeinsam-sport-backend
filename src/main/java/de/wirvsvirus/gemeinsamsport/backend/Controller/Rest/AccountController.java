package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dao.UserDao;
import de.wirvsvirus.gemeinsamsport.backend.Dto.AccountRequest;
import de.wirvsvirus.gemeinsamsport.backend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@RestController
public class AccountController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SecureRandom random = new SecureRandom();

    @PostMapping("/register/")
    public String register(@RequestBody AccountRequest accountRequest) {
        User existingUser = userDao.findByUsername(accountRequest.getUsername());
        if (null != existingUser) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken");
        }
        User newUser = new User();
        newUser.setUsername(accountRequest.getUsername());
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        String token = Base64Utils.encodeToUrlSafeString(bytes);
        newUser.setToken(passwordEncoder.encode(token));
        userDao.save(newUser);
        return token;
    }
}

