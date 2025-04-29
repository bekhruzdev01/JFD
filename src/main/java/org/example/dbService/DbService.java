package org.example.dbService;

import org.example.model.Country;
import org.example.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/first-jsp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result addCountry(String name) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_country(?, ?, ?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(2)).success(callableStatement.getBoolean(3)).build();
    }

    public Result deleteCountry(Integer id) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call delete_country(?,?,?)}");
        callableStatement.setInt(1, id);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(2)).success(callableStatement.getBoolean(3)).build();
    }

    public List<Country> countryList() throws SQLException {
        Connection connection = getConnection();
        ResultSet resultSet = connection.prepareStatement("select * from country order by id desc").executeQuery();
        List<Country> countries = new ArrayList<>();
        while (resultSet.next()) {
            countries.add(
                    Country.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .build()
            );
        }
        return countries;
    }
    public Result updateCountry(Country country) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call update_country(?, ?, ?, ?)}");
        callableStatement.setInt(1, country.getId());
        callableStatement.setString(2, country.getName());

        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(3)).build();
    }
}
