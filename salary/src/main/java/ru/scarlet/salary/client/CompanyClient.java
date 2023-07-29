package ru.scarlet.salary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "company-service", url = "${application.config.company-url}")
public interface CompanyClient {
//	@GetMapping("/api/v1/companies/{name}")
//	List<Company> findCom
}
