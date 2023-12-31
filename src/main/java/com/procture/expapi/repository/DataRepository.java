package com.procture.expapi.repository;

import com.procture.expapi.dto.DataCsvDto;
import com.procture.expapi.entity.DataCsv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<DataCsv, Long> {

    @Query("select new com.procture.expapi.dto.DataCsvDto(d.id, d.name, d.title) " +
            "from DataCsv d")
    List<DataCsvDto> findNameAndTitle();

}
