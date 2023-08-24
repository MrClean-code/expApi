package com.procture.expapi.servise;

import com.procture.expapi.dto.DataCsvDto;
import com.procture.expapi.dto.DataCsvFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataService {

    String save(MultipartFile file);

    List<DataCsvDto> findNameAndTitle();

    List<DataCsvFileDto> findFileData();
}
