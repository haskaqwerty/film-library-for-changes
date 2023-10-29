package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    public static final int ID_INDEX = 1;
    public static final int NAME_INDEX = 2;
    public static final int RELEASED_YEAR_INDEX = 3;
    public static final int DIRECTOR_FIRST_NAME_INDEX = 4;
    public static final int DIRECTOR_LAST_NAME_INDEX = 5;
    public static final int GENRE_INDEX = 6;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgresuser";
    private String password = "postgres";
    static String sqlExpression;

    //ArrayList<Movie>list = new ArrayList<Movie>(SimpleRepository.connectDb());
    @Override
    public Movie getMovieById(int movieId) {
        Movie result = null;
        sqlExpression = "select * from movies where id = ? limit 1";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = Movie.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(NAME_INDEX))
                    .releasedYear(resultSet.getInt(RELEASED_YEAR_INDEX))
                    .directorFirstName(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                    .directorLastName(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                    .genre(resultSet.getString(GENRE_INDEX))
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
    public List<Movie> getAll() {
        List<Movie> result = new ArrayList<>();
        sqlExpression = "select * from movies";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlExpression);
            while (resultSet.next()) {
                result.add(Movie.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(NAME_INDEX))
                        .releasedYear(resultSet.getInt(RELEASED_YEAR_INDEX))
                        .directorFirstName(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                        .directorLastName(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                        .genre(resultSet.getString(GENRE_INDEX))
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
    public boolean create(Movie movie) {
        boolean result = false;
        sqlExpression = "insert into movies values (?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX,movie.getId());
            preparedStatement.setString(NAME_INDEX,movie.getName());
            preparedStatement.setInt(RELEASED_YEAR_INDEX,movie.getReleasedYear());
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX,movie.getDirectorFirstName());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX,movie.getDirectorLastName());
            preparedStatement.setString(GENRE_INDEX,movie.getGenre());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) {
                System.out.println("Фильм не добавлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
                preparedStatement.close();
                connection.close();}
            System.out.println("Фильм добавлен");
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
    public boolean update(Movie movie, int id) {
        boolean result = false;
        sqlExpression = "update movies set name=?,releasedyear=?,directorfirstname=?,directorlastname=?,genre=?  where id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(NAME_INDEX-1,movie.getName());
            preparedStatement.setInt(RELEASED_YEAR_INDEX-1,movie.getReleasedYear());
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX-1,movie.getDirectorFirstName());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX-1,movie.getDirectorLastName());
            preparedStatement.setString(GENRE_INDEX-1,movie.getGenre());
            preparedStatement.setInt(ID_INDEX+5,id);
            int res = preparedStatement.executeUpdate();

            if (res == 0) {
                System.out.println("Фильм не обновлен");
                preparedStatement.close();
                connection.close();
                return result;
            } else{
            preparedStatement.close();
            connection.close();}
            System.out.println("Фильм обновлен");
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
        sqlExpression = "delete from movies where id = ? ";
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
