package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos;

import com.esgi.group5.jeeproject.domain.models.enums.HistorySearchType;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private HistorySearchType type;
    private String fields;
    private int resultCount;

    public HistoryDAO() {
    }

    public HistoryDAO(Long id, HistorySearchType type, String fields, int resultCount) {
        this.id = id;
        this.type = type;
        this.fields = fields;
        this.resultCount = resultCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistorySearchType getType() {
        return type;
    }

    public void setType(HistorySearchType type) {
        this.type = type;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
