package ru.scarlet.salary.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth-service", url = "${application.config.company-url}")

public interface AuthClient {
}
