package com.procture.expapi.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.procture.expapi.converter.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dataCsv", schema = "public")
@TypeDef(name = "json", typeClass = JsonStringType.class)
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
    @Type(type = "json")
    private JsonNode data;

}
