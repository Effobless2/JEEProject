package com.esgi.group5.jeeproject.domain.models;

import com.esgi.group5.jeeproject.domain.models.enums.HistorySearchType;

public class History extends EntityModel {
    private HistorySearchType type;
    private String fields;
    private int resultCount;

    public History() {
        super();
    }

    public History(Long id, HistorySearchType type, String fields, int resultCount) {
        super(id);
        this.type = type;
        this.fields = fields;
        this.resultCount = resultCount;
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
