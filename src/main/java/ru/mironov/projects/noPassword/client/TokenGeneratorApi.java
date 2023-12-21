package ru.mironov.projects.noPassword.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mironov.projects.noPassword.client.model.TokenApiResponse;
import ru.mironov.projects.noPassword.client.model.TokenRequest;
import ru.mironov.projects.noPassword.models.password.ApplicationName;

@FeignClient(name = "token-generator")
public interface TokenGeneratorApi {
    @GetMapping
    TokenApiResponse getToken(@RequestBody TokenRequest request);

}
