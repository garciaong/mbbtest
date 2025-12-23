package com.mbb.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig{
    @Value("${restclient.connection.timeout}")
    private int connectionTimeout;

    @Value("${restclient.read.timeout}")
    private int readTimeout;

    @Bean
    public RestClient restClient()
    {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);

        return RestClient.builder()
            .requestFactory(factory)
            .build();
    }
}
