package com.mbb.test.external;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import jakarta.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbb.test.dto.CategoryDTO;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private RestClient restClient;

    @Value("${restclient.external.api.url}")
    private String externalApiUrl;

    public static Map<Integer, CategoryDTO> categoryData = new HashMap<>();
    
    @PostConstruct
    public void getAllCategories() {
        Gson gson = new Gson();
        String response = restClient.get()
            .uri(externalApiUrl)
            .retrieve()
            .body(String.class);
        // Deserializing generic types
        Type listType = new TypeToken<List<CategoryDTO>>() {}.getType();
        List<CategoryDTO> categories = gson.fromJson(response, listType);
        categories.forEach(category -> {
            categoryData.put(category.getId(), category);
        });
    }
}
