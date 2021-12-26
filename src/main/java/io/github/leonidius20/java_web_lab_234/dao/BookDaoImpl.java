package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao{

    private static final String SELECT_ALL =
            "select * from books left join (select book, count(*) as \"taken_copies\" from book_requests group by book_requests.book) sub on books.id = sub.book";

    private static final String SELECT_BY_ID =
            SELECT_ALL + " where id = ?";

    private static final String DELETE_BY_ID = "delete from books where id = ?";

    private static final String UPDATE =
            "update books set name = ?, author_name = ?, year = ?, edition = ?, num_of_copies = ? where id = ?";

    private static final String INSERT =
            "insert into books (name, author_name, year, edition, num_of_copies) values (?, ?, ?, ?, ?)";

    public BookDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // @Override
    public List<Book> findAll(OrderBy orderBy) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL + orderBy.getSqlOrderBy());
        return resultSetToBooks(resultSet);
    }

    public List<Book> findByQuery(String query, FindBy findBy, OrderBy orderBy) throws SQLException {
        var statement = connection.prepareStatement(findBy.getSql() + orderBy.getSqlOrderBy());
        statement.setString(1, "%" + query + "%");
        if (findBy == FindBy.AUTHOR_OR_NAME) {
            statement.setString(2, "%" + query + "%");
        }
        ResultSet resultSet = statement.executeQuery();

        return resultSetToBooks(resultSet);
    }

    public Book findById(int id) throws SQLException {
        var statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        var resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSetToBook(resultSet);
        } else return null;
    }

    public void deleteById(int id) throws SQLException {
        var statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public boolean updateBook(Book book) throws SQLException {
        var statement = connection.prepareStatement(UPDATE);
        statement.setString(1, book.name());
        statement.setString(2, book.authorName());
        statement.setInt(3, book.year());
        statement.setInt(4, book.edition());
        statement.setInt(5, book.numberOfCopies());
        statement.setInt(6, book.id());

        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }

    public int createBook(Book book) throws SQLException {
        var statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, book.name());
        statement.setString(2, book.authorName());
        statement.setInt(3, book.year());
        statement.setInt(4, book.edition());
        statement.setInt(5, book.numberOfCopies());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) return -1;


        int id = -1;
        var generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) id = generatedKeys.getInt(1);
        return id;
    }

    private List<Book> resultSetToBooks(ResultSet resultSet) throws SQLException {
        List<Book> list = new LinkedList<>();

        while (resultSet.next()) {
            list.add(resultSetToBook(resultSet));
        }

        return list;
    }

    private Book resultSetToBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("year"),
                resultSet.getInt("num_of_copies"),
                resultSet.getString("author_name"),
                resultSet.getInt("edition"),
                resultSet.getInt("num_of_copies") - resultSet.getInt("taken_copies")
        );
    }



}
