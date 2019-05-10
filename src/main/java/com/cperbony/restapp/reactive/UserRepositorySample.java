package com.cperbony.restapp.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return Flux.fromIterable(this.users.values());
        // return Flux.fromStream(this.users.values().stream());
    }

    @Override
    public Mono<User> getUser(Integer id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Mono<Void> saveUser(Mono<User> userMono) {
        return getCreateUpdateUsers(userMono);
    }

    @Override
    public Mono<Void> updateUser(Mono<User> userMono) {
        return getCreateUpdateUsers(userMono);
    }


    private Mono<Void> getCreateUpdateUsers(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            users.put(user.getUserid(), user);
            System.out.format("Saved %s with id %d%n", user, user.getUserid());
        }).thenEmpty(Mono.empty());
    }


    @Override
    public Mono<Void> deleteUser(Integer id) {
        users.remove(id);
        System.out.println("user : " + users);
        return Mono.empty();
    }

}
