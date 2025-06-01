package org.pes.web_crud;

import java.util.List;

public class SearchRequest {
    private List<SearchCriteria> filters;
    private int page;
    private int size;
    private String sortBy;
    private String sortDir;
    public List<SearchCriteria> getFilters() {
        return filters;
    }
    public void setFilters(List<SearchCriteria> filters) {
        this.filters = filters;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public String getSortDir() {
        return sortDir;
    }
    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    
}