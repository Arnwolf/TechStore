package com.techstore.repositories;

import com.techstore.specifications.SqlSpecification;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void add(@NotNull final T entity) throws SQLException;
    void update(@NotNull final T entity) throws SQLException;
    void remove(@NotNull final T entity) throws SQLException;
    List<T> query(@NotNull final SqlSpecification spec) throws SQLException;
}
