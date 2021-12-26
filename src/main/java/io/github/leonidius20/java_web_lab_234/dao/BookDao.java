package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    List<Book> findAll(BookDaoImpl.OrderBy orderBy) throws SQLException;

    List<Book> findByQuery(String query, BookDaoImpl.FindBy findBy, BookDaoImpl.OrderBy orderBy) throws SQLException;

    Book findById(int id) throws SQLException;

    void deleteById(int id) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    int createBook(Book book) throws SQLException;

    public enum OrderBy  {

        NAME(" order by name"),
        AUTHOR(" order by author_name"),
        EDITION(" order by edition"),
        YEAR(" order by year");

        private final String sqlOrderBy;

        OrderBy(String sqlOrderBy) {
            this.sqlOrderBy = sqlOrderBy;
        }

        public String getSqlOrderBy() {
            return sqlOrderBy;
        }
    }

    public enum FindBy {

        AUTHOR_OR_NAME("select * from books where upper(name) like upper(?) or upper(author_name) like upper(?)"),
        AUTHOR("select * from books where upper(author_name) like upper(?)"),
        NAME("select * from books where upper(name) like upper(?)");

        private final String sql;

        FindBy(String sql) { this.sql = sql; }

        public String getSql() {
            return sql;
        }

    }

}
