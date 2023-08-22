package com.procture.expapi.servise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.procture.expapi.dto.DataCsvDto;
import com.procture.expapi.entity.DataCsv;
import com.procture.expapi.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final DataRepository dataRepository;

    public List<DataCsvDto> findNameAndTitle(){
        return dataRepository.findNameAndTitle();
    }

    @Override
    @Transactional
    public String save(MultipartFile file) {
        String data = readCsvFileAndConvertToString(file);
        String name = file.getOriginalFilename();
        StringBuilder title = readCsvFileTitle(file);

        DataCsv dataCsv = new DataCsv();
        dataCsv.setName(name);
        dataCsv.setData(data);
        dataCsv.setTitle(String.valueOf(title));

        dataRepository.save(dataCsv);
        return "успешно добавлен " + file.getOriginalFilename();
    }

    private StringBuilder readCsvFileTitle(MultipartFile file){

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = csvReader.readAll();
            String[] headerRow = rows.get(0);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < headerRow.length; i++) {
                if (i != headerRow.length - 1) {
                    stringBuilder.append(headerRow[i]);
                    stringBuilder.append(',');
                } else {
                    stringBuilder.append(headerRow[i]);
                }
            }

            return stringBuilder;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readCsvFileAndConvertToString(MultipartFile file) {

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = csvReader.readAll();

            List<Map<String, String>> jsonDataList = new ArrayList<>();

            String[] headerRow = rows.get(0);
            for (int i = 1; i < rows.size(); i++) {
                String[] dataRow = rows.get(i);
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headerRow.length && j < dataRow.length; j++) {
                    rowData.put(headerRow[j], dataRow[j]);
                }
                jsonDataList.add(rowData);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(jsonDataList);

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
