package com.skica.spvbpisvsportalbackend;

import com.skica.spvbpisvsportalbackend.controller.AssetController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan("com.skica.spvbpisvsportalbackend")
@WebMvcTest(AssetController.class)
class SampleGenericControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAssetEndpoints() throws Exception {
        MvcResult post = mockMvc.perform(MockMvcRequestBuilders
                        .post("/asset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" { \"name\":\"Test asset name\" }"))
                .andExpect(status().isCreated())
                .andReturn();

        String location = post.getResponse().getHeader("Location");
        Assertions.assertNotNull(location);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(location))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        mockMvc.perform(MockMvcRequestBuilders
                        .post(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" { \"name\":\"New test asset name\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/asset/list"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}