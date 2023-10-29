package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao{
    public static final int ID_INDEX = 1;
    public static final int NAME_INDEX = 2;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgresuser";
    private String password = "postgres";
    static String sqlExpression;
    @Override
    public Genre getGenreById(int id) {
        Genre result = null;
        sqlExpression = "select * from genres where id = ? limit 1";
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
    public boolean create(Genre genre) {
        boolean result = false;
        sqlExpression = "insert into genres values (?,?)";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX,genre.getId());
            preparedStatement.setString(NAME_INDEX,genre.getName());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) {
                System.out.println("Жанр не добавлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
                preparedStatement.close();
                connection.close();}
            System.out.println("Жанр добавлен");
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
    public boolean update(Genre genre, int id) {
        boolean result = false;
        sqlExpression = "update genres set name=?  where id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(NAME_INDEX-1,genre.getName());
            preparedStatement.setInt(ID_INDEX+1,id);
            int res = preparedStatement.executeUpdate();
            if (res == 0) {
                System.out.println("Жанр не обновлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
                preparedStatement.close();
                connection.close();}
            System.out.println("Жанр обновлен");
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
        sqlExpression = "delete from genres where id = ? ";
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
