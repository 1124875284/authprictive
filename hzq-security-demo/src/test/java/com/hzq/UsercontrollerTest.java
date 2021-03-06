package com.hzq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsercontrollerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void steup(){
        this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result=mockMvc.perform(MockMvcRequestBuilders.get("/user")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenGenInfoSuccess() throws Exception {
        String result=mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGenInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whencreatSuccess() throws Exception {
        Date date=new Date();
        System.out.println(date.getTime());
        String content="{\"username\":\"tom\",\"password\":\123\",\"birthday\":"+date.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    public void whenupdateSuccess() throws Exception {
        Date date=new Date();
        System.out.println(date.getTime());
        String content="{\"username\":\"tom\",\"password\":\123\",\"birthday\":"+date.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 文件上传
     * @throws Exception
     */
    @Test
    public void whenUploadSuccess() throws Exception {
        String result=mockMvc.perform(MockMvcRequestBuilders.fileUpload("file")
                .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello upload".getBytes("UTF-8")))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
