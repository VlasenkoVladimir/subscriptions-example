package com.vlasenko.subscriptions_example.service;

import com.vlasenko.subscriptions_example.domain.Subscription;
import com.vlasenko.subscriptions_example.dto.SubscriptionDto;
import com.vlasenko.subscriptions_example.mapper.SubscriptionMapper;
import com.vlasenko.subscriptions_example.repository.SubscriptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    @DisplayName("Get TOP subscriptions test")
    void findTopSubscriptions() {

        Subscription subscriptionVK = new Subscription();
        subscriptionVK.setId(1L);
        subscriptionVK.setSubscriptionName("VK");
        HashMap<String, String> vkMap = new HashMap<>(1);
        vkMap.put("vk", "VK");
        subscriptionVK.setParameters(vkMap);

        Subscription subscriptionNetflix = new Subscription();
        subscriptionNetflix.setId(2L);
        subscriptionNetflix.setSubscriptionName("Netflix");
        HashMap<String, String> netflixMap = new HashMap<>(1);
        netflixMap.put("netflix", "Netflix");
        subscriptionNetflix.setParameters(netflixMap);

        Subscription subscriptionYandex = new Subscription();
        subscriptionYandex.setId(3L);
        subscriptionYandex.setSubscriptionName("YANDEX");
        HashMap<String, String> yandexMap = new HashMap<>(1);
        yandexMap.put("yandex", "YANDEX");
        subscriptionYandex.setParameters(yandexMap);

        SubscriptionDto vkDto = new SubscriptionDto(subscriptionVK.getId(), subscriptionVK.getSubscriptionName(), vkMap);
        SubscriptionDto netflixDto = new SubscriptionDto(subscriptionNetflix.getId(), subscriptionNetflix.getSubscriptionName(), netflixMap);
        SubscriptionDto yandexDto = new SubscriptionDto(subscriptionYandex.getId(), subscriptionYandex.getSubscriptionName(), yandexMap);

        List<Subscription> subscriptions = List.of(subscriptionVK, subscriptionNetflix, subscriptionYandex);

        when(subscriptionRepository.findTopSubscriptions(any())).thenReturn(subscriptions);
        when(subscriptionMapper.toDto(subscriptionVK)).thenReturn(vkDto);
        when(subscriptionMapper.toDto(subscriptionNetflix)).thenReturn(netflixDto);
        when(subscriptionMapper.toDto(subscriptionYandex)).thenReturn(yandexDto);

        List<SubscriptionDto> result = subscriptionService.findTopSubscriptions(3);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("VK", result.get(0).subscriptionName());
        assertEquals("VK", result.get(0).parameters().get("vk"));
        assertEquals(2L, result.get(1).id());
        assertEquals("Netflix", result.get(1).subscriptionName());
        assertEquals("Netflix", result.get(1).parameters().get("netflix"));
        assertEquals(3L, result.get(2).id());
        assertEquals("YANDEX", result.get(2).subscriptionName());
        assertEquals("YANDEX", result.get(2).parameters().get("yandex"));
    }
}