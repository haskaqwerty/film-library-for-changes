package io.github.haskaqwerty.requestp.dao;

import io.github.haskaqwerty.requestp.pojo.Movie;

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
    public static final int COUNT_VAL = 6;
    static String sqlExpression;

    //ArrayList<Movie>list = new ArrayList<Movie>(SimpleRepository.connectDb());
    @Override
    public Movie getMovieById(Integer movieId) {
        Movie result = null;
        sqlExpression = "select * from movies where id = ?";
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
    public Movie create(Movie movie) {
        Movie result = null;
        sqlExpression = "insert into movies values (?,?,?,?,?,?) returning *";
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
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = (Movie.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(NAME_INDEX))
                    .releasedYear(resultSet.getInt(RELEASED_YEAR_INDEX))
                    .directorFirstName(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                    .directorLastName(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                    .genre(resultSet.getString(GENRE_INDEX))
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
    public Movie update(Movie movie, Integer id) {
        Movie result = null;
        sqlExpression = "update movies set name=?,releasedyear=?,directorfirstname=?,directorlastname=?,genre=?  where id = ? returning *";
        int move=1;
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setString(NAME_INDEX-move,movie.getName());
            preparedStatement.setInt(RELEASED_YEAR_INDEX-move,movie.getReleasedYear());
            preparedStatement.setString(DIRECTOR_FIRST_NAME_INDEX-move,movie.getDirectorFirstName());
            preparedStatement.setString(DIRECTOR_LAST_NAME_INDEX-move,movie.getDirectorLastName());
            preparedStatement.setString(GENRE_INDEX-move,movie.getGenre());
            preparedStatement.setInt(ID_INDEX+COUNT_VAL-move,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = (Movie.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(NAME_INDEX))
                    .releasedYear(resultSet.getInt(RELEASED_YEAR_INDEX))
                    .directorFirstName(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                    .directorLastName(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                    .genre(resultSet.getString(GENRE_INDEX))
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
    public Movie delete(Integer id) {
        Movie result = null;
        sqlExpression = "delete from movies where id = ? returning *";
        Connection connection = null;
        try {
            connection = ConnectionManagerImpl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlExpression);
            preparedStatement.setInt(ID_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = (Movie.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(NAME_INDEX))
                    .releasedYear(resultSet.getInt(RELEASED_YEAR_INDEX))
                    .directorFirstName(resultSet.getString(DIRECTOR_FIRST_NAME_INDEX))
                    .directorLastName(resultSet.getString(DIRECTOR_LAST_NAME_INDEX))
                    .genre(resultSet.getString(GENRE_INDEX))
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

}
