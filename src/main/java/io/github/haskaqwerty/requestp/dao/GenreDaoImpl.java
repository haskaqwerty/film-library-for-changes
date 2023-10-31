package io.github.haskaqwerty.requestp.dao;

import io.github.haskaqwerty.requestp.pojo.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao{
    public static final int ID_INDEX = 1;
    public static final int NAME_INDEX = 2;
    public static final int COUNT_VAL = 2;

    static String sqlExpression;
    @Override
    public Genre getGenreById(Integer id) {
        Genre result = null;
        sqlExpression = "select * from genres where id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Genre.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(NAME_INDEX))
                    .build();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return result;

}

    @Override
    public List<Genre> getAll() {
        List<Genre> result = new ArrayList<>();
        sqlExpression = "select * from genres";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlExpression);
            while (resultSet.next()) {
                result.add(Genre.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(NAME_INDEX))
                        .build());
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }


        return result;
    }

    @Override
    public Genre create(Genre genre) {
        Genre result = null;
        sqlExpression = "insert into genres values (?,?) returning *";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX,genre.getId());
            preparedStatement.setString(NAME_INDEX,genre.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Genre.builder()
                    .id(resultSet.getInt(ID_INDEX))
                    .name(resultSet.getString(NAME_INDEX))
                    .build();
                resultSet.close();
                preparedStatement.close();
                connection.close();
            return  result;
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public Genre update(Genre genre, Integer id) {
        Genre result = null;
        sqlExpression = "update genres set name=?  where id = ? returning *";
        int move=1;
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(NAME_INDEX-move,genre.getName());
            preparedStatement.setInt(ID_INDEX+COUNT_VAL-move,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Genre.builder()
                    .id(resultSet.getInt(ID_INDEX))
                    .name(resultSet.getString(NAME_INDEX))
                    .build();
                resultSet.close();
                preparedStatement.close();
                connection.close();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Genre delete(Integer id) {
        Genre result = null;
        sqlExpression = "delete from genres where id = ? returning *";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Genre.builder()
                    .id(resultSet.getInt(ID_INDEX))
                    .name(resultSet.getString(NAME_INDEX))
                    .build();
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            try {
                connection.close();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return result;
    }
}
