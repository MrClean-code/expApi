package com.procture.expapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procture.expapi.dto.DataCsvFileDto;
import com.procture.expapi.repository.DataRepository;
import com.procture.expapi.servise.DataServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DataServiceImplTest {
    @Mock
    private DataRepository dataRepository;
    @InjectMocks
    private DataServiceImpl dataService;

    @Test
    @DisplayName("найти файл по id")
    void soundFindFileDataById(){
        JsonNode data = new ObjectMapper().valueToTree(getOldPersonData());
        JsonNode data2 = new ObjectMapper().valueToTree(getYoungPersonData());
        Long id = 2L;

        List<DataCsvFileDto> dataCsvFileDtoList = List.of(
                new DataCsvFileDto(1L, data),
                new DataCsvFileDto(2L, data2)
        );

        Mockito.when(dataRepository.findFileData()).thenReturn(dataCsvFileDtoList);

        List<DataCsvFileDto> dataTest = dataService.findFileDataForId(id);

        Assertions.assertNotNull(dataTest);
        Assertions.assertEquals(1, dataTest.size());
        Assertions.assertEquals(dataCsvFileDtoList.get(1), dataTest.get(0));

    }

    private List<Map<String, String>> getYoungPersonData(){
        List<Map<String, String>> youngPersonList = List.of(
                new HashMap<>() {{
                    put("Age", "12");
                    put("Height", "150");
                    put("Weight", "40");
                }}
        );
        return youngPersonList;
    }

    private List<Map<String, String>> getOldPersonData(){
        List<Map<String, String>> oldPersonList = List.of(
                new HashMap<>() {{
                    put("Age", "22");
                    put("Height", "165");
                    put("Weight", "59");
                }},
                new HashMap<>() {{
                    put("Age", "60");
                    put("Height", "175");
                    put("Weight", "79");
                }}
        );
        return oldPersonList;
    }

}
