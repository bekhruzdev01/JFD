package org.example.dbService;

import org.example.model.Book;
import org.example.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/first-jsp";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root123";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            System.err.println("Xatolik");
            return null;
        }
    }

    public List<Book> getBooks() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from book order by id desc");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            books.add(
                    Book.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getDouble("price"))
                            .writer(resultSet.getString("writer"))
                            .year(resultSet.getInt("year"))
                            .build()
            );
        }
        return books;
    }

    public Result addBook(Book book) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_book(?, ?, ?, ?, ?, ?)}");
        callableStatement.setString(1, book.getName());
        callableStatement.setDouble(2, book.getPrice());
        callableStatement.setString(3, book.getWriter());
        callableStatement.setInt(4, book.getYear());
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.registerOutParameter(6, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder()
                .message(callableStatement.getString(5))
                .success(callableStatement.getBoolean(6))
                .build();
    }

    public Result editBook(Book book) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call edit_book(?, ?, ?, ?, ?, ?)}");
        callableStatement.setString(1, book.getName());
        callableStatement.setDouble(2, book.getPrice());
        callableStatement.setString(3, book.getWriter());
        callableStatement.setInt(4, book.getYear());
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.registerOutParameter(6, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder()
                .message(callableStatement.getString(5))
                .success(callableStatement.getBoolean(6))
                .build();
    }
}
