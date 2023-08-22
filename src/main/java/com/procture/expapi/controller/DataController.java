package com.procture.expapi.controller;

import com.procture.expapi.dto.DataCsvDto;
import com.procture.expapi.entity.DataCsv;
import com.procture.expapi.servise.DataService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @PostMapping( "/new")
    public ResponseEntity<String> addFile(@RequestParam("fileCsv") MultipartFile file) {
        if (file == null || !file.getOriginalFilename().endsWith("csv")) {
            throw new NullPointerException("файл пуст или неверный формат");
        }
        return new ResponseEntity(dataService.save(file),
                HttpStatus.CREATED);
    }

    @GetMapping("/name-title")
    private ResponseEntity<List<DataCsvDto>> getData(){
        return new ResponseEntity<>(dataService.findNameAndTitle(),
                HttpStatus.OK);
    }
}

