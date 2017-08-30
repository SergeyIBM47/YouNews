package com.template.security;

import com.template.domain.model.User;
import com.template.domain.model.enums.OnlineStatus;
import com.template.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;

/**
 * @author Created by svolokh@rightandabove.com at ${DATE}
 * @author Last modified by $Author: $author $ <br>
 * @author Last modified on $Date: $date $ at revision $Revision: $revision $ <br>
 *         This class loads the requested user by using a Spring Data JPA repository.
 */
public class LoadUserSpecificDataFormLoginService implements UserDetailsService {

    private static final String SPRING_SECURITY_USERNAME_KEY = "authUserEmail";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadUserSpecificDataFormLoginService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    public HttpSession session;


    public LoadUserSpecificDataFormLoginService() {
    }

    /**
     * Loads the user information.
     *
     * @param username The username of the requested user.
     * @return The information of the user.
     * @throws UsernameNotFoundException Thrown if no user is found with the given username.
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {
        LOGGER.debug("Loading user by username: {}", username);

        User user = repository.findByEmail(username);
        LOGGER.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        user.setOnlineStatus(OnlineStatus.ONLINE);
        repository.save(user);

        if (session != null) {
            session.setAttribute(SPRING_SECURITY_USERNAME_KEY, user.getEmail());
        }

        LOGGER.debug("User with E-mail: {} authorized, set status Online - true", user.getEmail());

        UserDetails principal = UserDetails.getBuilder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        return principal;
    }

}
