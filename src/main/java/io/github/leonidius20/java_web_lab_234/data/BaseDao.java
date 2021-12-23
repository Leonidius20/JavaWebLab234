package io.github.leonidius20.java_web_lab_234.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {

    protected Connection connection;

    public abstract List<T> findAll() throws SQLException;

}
