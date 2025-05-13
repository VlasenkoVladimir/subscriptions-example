package com.vlasenko.subscriptions_example.service;

import com.vlasenko.subscriptions_example.domain.Subscription;
import com.vlasenko.subscriptions_example.domain.User;
import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import com.vlasenko.subscriptions_example.dto.UserDto;
import com.vlasenko.subscriptions_example.mapper.SubscriptionMapper;
import com.vlasenko.subscriptions_example.mapper.UserMapper;
import com.vlasenko.subscriptions_example.repository.SubscriptionRepository;
import com.vlasenko.subscriptions_example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User service
 */

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    /**
     * Create a new user
     *
     * @param userDto with user details
     * @return userDto if exist correctly
     */
    public UserDto create(UserDto userDto) {
        logger.debug("Called create() of UserService with UserDto: {}", userDto);
        return userMapper.toDto(save(userMapper.toEntity(userDto)));
    }

    /**
     * Find user
     *
     * @param id user id
     * @return UserDto if success
     */
    public UserDto getById(long id) {
        logger.debug("Called getById() of UserService with User id: {}", id);
        return userMapper.toDto(getUserById(id));
    }

    /**
     * Update user data
     *
     * @param userDto new data of existed user
     * @return updated UserDto
     */
    public UserDto update(UserDto userDto, long id) {
        logger.debug("Try to update user with dto: {}", userDto);
        try {
            User user = userRepository.findById(id).orElseThrow();
                if (!userDto.firstName().isBlank()) {
                    user.setFirstName(userDto.firstName());
                }
                if (!userDto.middleName().isBlank()) {
                    user.setMiddleName(userDto.middleName());
                }
                if (!userDto.lastName().isBlank()) {
                    user.setLastName(userDto.lastName());
                }
                if (userDto.username().isBlank()) {
                    user.setUsername(userDto.username());
                }
                if (!userDto.subscriptions().isEmpty()) {
                    user.setSubscriptions(userDto.subscriptions().stream().map(subscriptionMapper::toEntity).toList());
                }
                return userMapper.toDto(userRepository.save(user));
        } catch (Exception ex) {
            logger.info("Can't update user with reason: {}", ex.getMessage());
            throw new RuntimeException("Can't update user with dto: " + userDto);
        }
    }

    /**
     * Delete existed user
     *
     * @param id of existed user
     * @return true if success
     */
    public boolean deleteUserById(long id) {
        logger.debug("Try to delete user with id: {}", id);
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            logger.info("Can't delete user with reason: {}", ex.getMessage());
            throw new RuntimeException("Can't delete user by id: " + id);
        }
    }

    /**
     * Get user by its id from a repository
     *
     * @param id existed user's id
     * @return User
     */
    public User getUserById(long id) {
        logger.debug("Try to get user with id: {}", id);
        try {
            return userRepository.findById(id).orElseThrow();
        } catch (Exception ex) {
            logger.info("Can't get user by id: {} with reason: {}", id, ex.getMessage());
            throw new RuntimeException("Can't get user by id: " + id);
        }
    }

    /**
     * Save user to repository
     *
     * @param user to save
     * @return saved entity
     */
    public User save(User user) {
        logger.debug("Try to save user: {}", user);
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            logger.info("Can't save user: {}, with reason: {}", user, ex.getMessage());
            throw new RuntimeException("Can't save user: " + user);
        }
    }

    /**
     * Add subscription to user
     *
     * @param subscriptionDto is SubscriptionDto
     * @param userId         id of existed user
     * @return true when success
     */
    public boolean addSubscriptionToUser(long userId, SubscriptionDto subscriptionDto) {
        logger.debug("Try to add subscription: {} to user with id: {}", subscriptionDto, userId);
        try {
            Subscription sub = subscriptionRepository.findBySubscriptionName(subscriptionDto.subscriptionName()).orElseThrow();
            User user = getUserById(userId);
            List<Subscription> subscriptions = user.getSubscriptions();
            subscriptions.add(sub);
            save(user);
            return true;
        } catch (Exception ex) {
            logger.info("Can't add subscription: {} to user with id: {} with reason: {}", subscriptionDto, userId, ex.getMessage());
            throw new RuntimeException("Can't add subscription: " + subscriptionDto + " to user with id: " + userId);
        }
    }

    /**
     * Get all user's subscriptions
     *
     * @param userId id of existed user
     * @return List of SubscriptionDto
     */
    public List<SubscriptionDto> getAllUserSubscriptions(long userId) {
        logger.debug("Try to get getAllUserSubscriptions with user id: {}", userId);
        List<Subscription> subscriptions;
        try {
            User user = userRepository.findById(userId).orElseThrow();
            subscriptions = user.getSubscriptions();
            return subscriptions.stream()
                    .map(subscriptionMapper::toDto)
                    .toList();
        } catch (Exception ex) {
            logger.info("Can't get all subscriptions of user with id: {} with reason: {}", userId, ex.getMessage());
            throw new RuntimeException("Can't read subscriptions by user id: " + userId);
        }
    }

    /**
     * Remove subscription by id for user with id
     * @param id user ID
     * @param sub_id subscription ID
     * @return true if success
     */
    public boolean removeSubscriptionByIdForUserById(long id, long sub_id) {
        logger.debug("Try to delete subscription with id: {} for user with id: {}", sub_id, id);
        try {
            User user = userRepository.findById(id).orElseThrow();
            List<Subscription> subscriptions = user.getSubscriptions();
            Subscription subscription = subscriptionRepository.findById(sub_id).orElseThrow();

            subscriptions.remove(subscription);
            user.setSubscriptions(subscriptions);
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            logger.info("Can't remove subscription with id: {} for user with id: {} with reason: {}", sub_id, id, ex.getMessage());
            throw new RuntimeException("Can't remove subscription with id: " + sub_id + " for user with id: " + id);
        }
    }
}