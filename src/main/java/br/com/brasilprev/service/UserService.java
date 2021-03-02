package br.com.brasilprev.service;

import br.com.brasilprev.model.User;

public interface UserService {

    User findByUsername(String username);
}
