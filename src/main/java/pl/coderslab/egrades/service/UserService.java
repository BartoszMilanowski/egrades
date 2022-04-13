package pl.coderslab.egrades.service;

import pl.coderslab.egrades.entity.User;

public interface UserService {

    User findByEmail(String email);

}
