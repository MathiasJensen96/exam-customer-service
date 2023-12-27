package com.example.CustomerService.service;

import com.example.CustomerService.model.dto.NewOrder;
import com.example.CustomerService.model.dto.StartOrderProcess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class CamundaService {
    private final RestTemplate restTemplate;

    @Value("${camunda.server.engine}")
    private String restEngine;

    @Value("${camunda.server.definition.key}")
    private String processDefinitionKey;

    private final ObjectMapper mapper;

    public String startOrderProcess(String customerId, NewOrder newOrder) {
        int parsedId = Integer.parseInt(customerId);
        newOrder.setCustomerId(parsedId);

        String startProcessURL = new StringBuilder(restEngine)
                .append("process-definition/key/")
                .append(processDefinitionKey)
                .append("/start")
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StartOrderProcess camundaRequest = null;
        try {
            camundaRequest = StartOrderProcess.builder()
                    .variables(StartOrderProcess.OrderHolder
                            .builder()
                            .order(StartOrderProcess.CamundaOrder
                                    .builder()
                                    .value(mapper.writeValueAsString(newOrder))
                                    .type("json")
                                    .build())
                            .build())
                    .build();
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        HttpEntity<StartOrderProcess> request = new HttpEntity<>(camundaRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(startProcessURL, request, String.class);

        return response.getBody();
    }
}