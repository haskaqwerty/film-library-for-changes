package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Director;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectorDaoImpl implements DirectorDao {
    public static final int ID_INDEX = 1;
    public static final int DIRECTOR_FIRST_NAME_INDEX = 2;
    public static final int DIRECTOR_LAST_NAME_INDEX = 3;
    public static final int DATE_INDEX = 4;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgresuser";
    private String password = "postgres";
    static String sqlExpression;

    @Override
    public Director getDirectorById(int id) {
        Director result = null;
        sqlExpression = "select * from directors where id = ? limit 1";
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
    public boolean create(Director director) {
        boolean result = false;
        sqlExpression = "insert into directors values (?,?,?,?)";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX,director.getId());
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX,director.getFirstname());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX,director.getLastname());
            preparedStatement.setDate(DATE_INDEX,(Date) director.getBirthdayDate());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) {
                System.out.println("Режиссер не добавлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
                preparedStatement.close();
                connection.close();}
            System.out.println("Режиссер добавлен");
            result = true;
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
    public boolean update(Director director, int id) {
        boolean result = false;
        sqlExpression = "update directors set firstname=?,lastname=?,datebirthday=? where id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX-1,director.getFirstname());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX-1,director.getLastname());
            preparedStatement.setDate(DATE_INDEX-1,(Date)director.getBirthdayDate());
            preparedStatement.setInt(ID_INDEX+3,id);
            int res = preparedStatement.executeUpdate();

            if (res == 0) {
                System.out.println("Режиссер не обновлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
            preparedStatement.close();
            connection.close();}
            System.out.println("Режиссер обновлен");
            result = true;
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
    public boolean delete(int id) {
        boolean result = false;
        sqlExpression = "delete from directors where id = ? ";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            int res = preparedStatement.executeUpdate();
            if (res != 0) {
                result=true;
            }
            preparedStatement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            try {
                connection.close();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }


    }

}
