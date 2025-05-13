package com.vlasenko.subscriptions_example.controller.rest.v1;

import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import com.vlasenko.subscriptions_example.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Subscription endpoints
 */

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    private final SubscriptionService subscriptionService;

    @Value("${app.top.limit}")
    private int LIMIT;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    //    GET /subscriptions/top - получить ТОП популярных подписок
    @GetMapping(path = "/subscriptions/top")
    public List<SubscriptionDto> getTopSubscriptions() {
        logger.debug("Try to get top subscriptions with limit: {}", LIMIT);

        return subscriptionService.findTopSubscriptions(LIMIT);
    }
}
