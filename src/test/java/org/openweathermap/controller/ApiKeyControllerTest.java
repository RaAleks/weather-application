package org.openweathermap.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openweathermap.controller.v1.ApiKeyController;
import org.openweathermap.service.CacheService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApiKeyControllerTest {

    private MockMvc mockMvc;
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = mock(CacheService.class);
        ApiKeyController controller = new ApiKeyController(cacheService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testAddApiKey() throws Exception {
        String apiKey = "test-api-key";
        String username = "john";

        mockMvc.perform(put("/v1/api-key/{apiKey}", apiKey)
                        .param("username", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cacheService, times(1)).putKey(apiKey, username);
    }

    @Test
    void testRemoveApiKey() throws Exception {
        String username = "john";

        mockMvc.perform(delete("/v1/api-key")
                        .param("username", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cacheService, times(1)).removeKey(username);
    }
}