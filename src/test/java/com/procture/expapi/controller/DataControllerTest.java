package com.procture.expapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procture.expapi.entity.DataCsv;
import com.procture.expapi.servise.DataService;
import com.procture.expapi.servise.DataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@WebMvcTest(controllers = DataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataService dataService;
    @Autowired
    private ObjectMapper objectMapper;

    private DataCsv dataCsv;
    private static final String FILE_PATH = "src/test/resources/static/tableConvert.csv";
    private static final String CONTENT_TYPE = "text/csv";
    private Path path;
    private String fileName;
    private byte[] fileContent;

    @Test
    void addFileTest() throws Exception {
        path = Paths.get(FILE_PATH);
        fileName = path.getFileName().toString();
        fileContent = Files.readAllBytes(path);

        MockMultipartFile mockFile = new MockMultipartFile("fileCsv", fileName,
                CONTENT_TYPE, fileContent);

        given(dataService.createFile(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(multipart("/file/new")
                .file(mockFile));

        // exc because don't cast to DataCsv
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
