package org.pes.web_crud;

public class SearchCriteria {
    private String key;       // Field name (e.g., "price", "category")
    private String operation; // Comparison operator (e.g., ">", "<", ":")
    private Object value;     // Value to filter against

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    // Getters and Setters
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
}