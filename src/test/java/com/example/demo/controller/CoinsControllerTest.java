package com.example.demo.controller;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.service.CoinService;
import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;

@WebMvcTest(CoinsController.class)
class CoinsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinService coinService;

    @MockBean
    private UserService userService;

    @MockBean
    private RequestService requestService;

    @Autowired
    private WebApplicationContext context; // Add this line to get the WebApplicationContext

    @BeforeEach
    void setUp() {
        // You can also configure mockMvc with standaloneSetup to exclude some components
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetCoinDetails_ValidUser() throws Exception {
        // Mock the service methods
        when(userService.isValidUserExist(anyLong())).thenReturn(true);
        when(coinService.getCoinDetails(anyString())).thenReturn("Mocked coin details");

        ResultActions result = mockMvc.perform(get("/coins")
                .header("X-CMC_PRO_API_KEY", "mock-api-key")
                .param("userId", "123"));

        result.andExpect(status().isOk())
              .andExpect(content().string("Mocked coin details"));
    }

    @Test
    void testGetCoinDetails_InvalidUser() throws Exception {
        // Mock the service methods
        when(userService.isValidUserExist(anyLong())).thenReturn(false);

        ResultActions result = mockMvc.perform(get("/coins")
                .header("X-CMC_PRO_API_KEY", "mock-api-key")
                .param("userId", "456"));

        result.andExpect(status().isBadRequest());
    }
}
