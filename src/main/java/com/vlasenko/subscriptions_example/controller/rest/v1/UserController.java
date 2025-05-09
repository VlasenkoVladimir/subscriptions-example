package com.vlasenko.subscriptions_example.controller.rest.v1;

import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import com.vlasenko.subscriptions_example.dto.UserDto;
import com.vlasenko.subscriptions_example.repository.SubscriptionRepository;
import com.vlasenko.subscriptions_example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * User endpoints
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final SubscriptionRepository subscriptionRepository;

    @Value("${app.top.limit}")
    private long LIMIT;

    public UserController(UserService userService, SubscriptionRepository subscriptionRepository) {
        this.userService = userService;
        this.subscriptionRepository = subscriptionRepository;
    }

    //    POST /users - создать пользователя
    @PostMapping
    @ResponseStatus(CREATED)
    public UserDto create(@RequestBody UserDto newUser) {

        logger.info("Try to create new user: {}", newUser);

        return userService.create(newUser);
    }

    //    GET /users/{id} - получить информацию о пользователе
    @GetMapping(path = "/{id}")
    public UserDto getById(@PathVariable long id) {

        logger.info("Try to get existed user with id: {}", id);

        return userService.getById(id);
    }

    //    PUT /users/{id} - обновить пользователя
    @PutMapping(path = "/{id}")
    public UserDto update(@RequestBody UserDto changedUser) {

        logger.info("Try to update with UserDto: {}", changedUser);

        return userService.update(changedUser);
    }

    //    DELETE /users/{id} - удалить пользователя
    @DeleteMapping(path = "/{id}")
    public boolean deleteById(@PathVariable long id) {

        logger.info("Try to delete user with id: {}", id);

        return userService.deleteUserById(id);
    }

    //    POST /users/{id}/subscriptions - добавить подписку
    @PostMapping(path = "/{id}/subscriptions")
    public boolean addSubscriptionToUser(@PathVariable long id, @RequestBody SubscriptionDto sub) {

        logger.info("Try to add subscription: {} to user with id: {}", sub, id);

        return userService.addSubscriptionToUser(id, sub);
    }

    //    GET /users/{id}/subscriptions - получить подписки пользователя
    @GetMapping(path = "/{id}/subscriptions")
    public List<SubscriptionDto> getAllUserSubscriptions(@PathVariable long id) {

        logger.info("Try to get all subscriptions for user with id: {}", id);

        return userService.getAllUserSubscriptions(id);
    }
//    DELETE /users/{id}/subscriptions/{sub_id} - удалить подписку
    @DeleteMapping(path = "/{id}/subscriptions/{sub_id}")
    public boolean deleteSubscriptionToUser(@PathVariable long id, @PathVariable long sub_id) {

        logger.info("Try to delete subscription with id: {} for user with id: {}", sub_id, id);

        return userService.removeSubscriptionByIdForUserById(id, sub_id);
    }
//    GET /subscriptions/top - получить ТОП-3 популярных подписок
//    @GetMapping(path = "/subscriptions/top")
//    public List<SubscriptionDto> getTopSubscriptions() {
//
//        logger.debug("Try to get top subscriptions with limit: {}", LIMIT);
//
//        return subscriptionRepository.getTopWithLimit(LIMIT);
//    }
}