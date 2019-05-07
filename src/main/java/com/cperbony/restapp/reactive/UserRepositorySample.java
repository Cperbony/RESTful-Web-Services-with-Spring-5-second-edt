package com.cperbony.restapp.reactive;

import reactor.core.publisher.Flux;

import java.util.Map;

public class UserRepositorySample implements UserRepository {

    //Initialize users
    private Map<Integer, User> users = null;

    public UserRepositorySample() {
        users = Map.of(
                1, (new User(1, "Lucas")),
                2, (new User(2, "Sarah")),
                3, (new User(3, "Claus")),
                4, (new User(4, "Náthila"))
        );
    }

    @Override
    public Flux<User> getAllUsers() {
        return Flux.fromStream(this.users.values().stream());
    }
}
