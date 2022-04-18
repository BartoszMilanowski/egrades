package pl.coderslab.egrades.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.egrades.entity.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities, pl.coderslab.egrades.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.coderslab.egrades.entity.User getUser(){
        return user;
    }
}
