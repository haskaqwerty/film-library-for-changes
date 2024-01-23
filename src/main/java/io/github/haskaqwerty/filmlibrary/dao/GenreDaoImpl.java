package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao{
    private JdbcTemplate jdbc;
    @Autowired
    public GenreDaoImpl(DataSource dataSource) {
        jdbc = new JdbcTemplate(dataSource);
    }

    class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Genre.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
    }
    @Override
    public Genre getGenreById(Integer id){
        return jdbc.queryForObject("select * from genres where id = ?",
                new Object[] {id}, new GenreDaoImpl.GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from movies", new GenreDaoImpl.GenreMapper());
    }

    @Override
    public boolean create(Genre genre) {
        return jdbc.update("insert into genres values (?,?)",
                genre.getId(), genre.getName())>0;
    }

    @Override
    public boolean update(Genre genre, Integer id) {
        return jdbc.update("update genres set name=? where id = ?",
                genre.getName(), genre.getId())>0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbc.update("delete from genres where id = ?",id)>0;
    }

}
