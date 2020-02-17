package com.techstore.specifications;

@FunctionalInterface
public interface SqlSpecification {
    int SQL_LIMIT = 10;
    String toSql();
}
