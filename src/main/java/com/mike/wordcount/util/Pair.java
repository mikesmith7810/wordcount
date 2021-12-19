package com.mike.wordcount.util;

public class Pair {

    private Object key;
    private Object value;

    public Pair(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object[] getPair() {
        return new Object[] { this.key, this.value};
    }

    @Override
    public boolean equals(Object pair) {
        boolean keysMatch = false;
        boolean valuesMatch = false;
        if (((Pair)pair).getKey().equals(this.getKey()))
            keysMatch = true;
        if (((Pair)pair).getValue().equals(this.getValue()))
            valuesMatch = true;

        return keysMatch && valuesMatch;
    }
}