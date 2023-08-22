package com.procture.expapi.entity;

import com.procture.expapi.dto.DataCsvDto;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dataCsv", schema = "public")
public class DataCsv {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "data", columnDefinition = "text")
    private String data;

}
