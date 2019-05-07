package com.cperbony.restapp.reactive;

import reactor.core.publisher.Flux;

public interface UserRepository {
    Flux<User> getAllUsers();
}
