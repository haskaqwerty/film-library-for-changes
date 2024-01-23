package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Director;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorDaoImpl implements DirectorDao {

    @Override
    public Director getDirectorById(Integer id) {
        Director result = null;
        sqlExpression = "select * from directors where id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Director.builder()
                    .id(resultSet.getInt(ID_INDEX))
                    .firstname(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                    .lastname(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                    .birthdayDate(resultSet.getDate(DATE_INDEX))
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
    public List<Director> getAll() {
        List<Director> result = new ArrayList<>();
        sqlExpression = "select * from directors";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlExpression);
            while (resultSet.next()) {
                result.add(Director.builder()
                        .id(resultSet.getInt(ID_INDEX))
                        .firstname(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                        .lastname(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                        .birthdayDate(resultSet.getDate(DATE_INDEX))
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
    public Director create(Director director) {
        Director result = null;
        sqlExpression = "insert into directors values (?,?,?,?) returning *";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX,director.getId());
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX,director.getFirstname());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX,director.getLastname());
            preparedStatement.setDate(DATE_INDEX,(Date) director.getBirthdayDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                result = (Director.builder()
                        .id(resultSet.getInt(ID_INDEX))
                        .firstname(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                        .lastname(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                        .birthdayDate(resultSet.getDate(DATE_INDEX))
                        .build());
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
        return  result;
    }

    @Override
    public Director update(Director director, Integer id) {
        Director result = null;
        sqlExpression = "update directors set firstname=?,lastname=?,datebirthday=? where id = ? returning *";
        int move=1;
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX-move,director.getFirstname());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX-move,director.getLastname());
            preparedStatement.setDate(DATE_INDEX-move,(Date)director.getBirthdayDate());
            preparedStatement.setInt(ID_INDEX+COUNT_VAL-move,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                result = (Director.builder()
                        .id(resultSet.getInt(ID_INDEX))
                        .firstname(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                        .lastname(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                        .birthdayDate(resultSet.getDate(DATE_INDEX))
                        .build());
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
    public Director delete(Integer id) {
        Director result = null;
        sqlExpression = "delete from directors where id = ? returning *";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                result = (Director.builder()
                        .id(resultSet.getInt(ID_INDEX))
                        .firstname(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                        .lastname(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                        .birthdayDate(resultSet.getDate(DATE_INDEX))
                        .build());
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
