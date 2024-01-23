package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {
    private JdbcTemplate jdbc;
    @Autowired
    public MovieDaoImpl(DataSource dataSource) {
        jdbc = new JdbcTemplate(dataSource);
    }

    class MovieMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Movie.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .releasedYear(resultSet.getInt("releasedyear"))
                    .directorFirstName(resultSet.getString("directorfirstname"))
                    .directorLastName(resultSet.getString("directorlastname"))
                    .genre(resultSet.getString("genre"))
                    .build();
        }
    }

    @Override
    public Movie getMovieById(Integer movieId){
        return jdbc.queryForObject("select * from movies where id = ?",
                new Object[] {movieId}, new MovieMapper());
    }

    @Override
    public List<Movie> getAll() {
        return jdbc.query("select * from movies", new MovieMapper());
    }

    @Override
    public boolean create(Movie movie) {
        return jdbc.update("insert into movies values (?,?,?,?,?,?)",
                  movie.getId(),
                  movie.getName(),
                  movie.getReleasedYear(),
                  movie.getDirectorFirstName(),
                  movie.getDirectorLastName(),
                  movie.getGenre())>0;
    }

    @Override
    public boolean update(Movie movie, Integer id) {
        return jdbc.update("update movies set name=?,releasedyear=?,directorfirstname=?,directorlastname=?,genre=?  where id = ?",
                movie.getName(),
                movie.getReleasedYear(),
                movie.getDirectorFirstName(),
                movie.getDirectorLastName(),
                movie.getGenre(),
                movie.getId())>0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbc.update("delete from movies where id = ?",id)>0;
    }

}
