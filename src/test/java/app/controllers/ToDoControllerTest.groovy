package app.controllers

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringJUnit4ClassRunner.class)

    @WebAppConfiguration
    public class RestControllerTest {

        @Autowired
        private WebApplicationContext context;

        private MockMvc mockMvc;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }


       /* @Test
        public void getUserList() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(content().encoding("UTF-8"))
                    .andExpect(jsonPath("[0].id").exists())
                    .andExpect(jsonPath("[0].task").exists())
                    .andExpect(jsonPath("[0].name").exists())
            )
        }*/
    }
