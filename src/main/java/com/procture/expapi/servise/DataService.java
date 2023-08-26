package com.procture.expapi.servise;

import com.procture.expapi.dto.DataCsvDto;
import com.procture.expapi.dto.DataCsvFileDto;
import com.procture.expapi.entity.DataCsv;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataService {

    DataCsv createFile(MultipartFile file);

    List<DataCsvDto> findNameAndTitle();

    List<DataCsvFileDto> findFileData();

    List<DataCsvFileDto> findFileDataForId(Long id);
}
