package ua.supplementsList.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.supplementsList.dao.interfaces.ClassificationDAO;
import ua.supplementsList.models.Classification;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClassificationDAOImpl implements ClassificationDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insertClassification(Classification classification) {
        String sql = "INSERT INTO classifications (name) VALUES (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", classification.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public Classification getClassification(int id) {
        String sql = "SELECT * FROM classifications WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ClassificationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Classification getClassification(String name) {
        String sql = "SELECT * FROM classifications WHERE name=:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ClassificationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateClassification(Classification classification) {
        String sql = "UPDATE classifications SET name=:name WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", classification.getName());
        params.addValue("id", classification.getId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeClassification(int id) {
        String sql = "DELETE FROM classifications WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public int getClassificationCount(int id) {
        String sql = "SELECT COUNT(classification_id) FROM supplementInfo WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    private static final class ClassificationMapper implements RowMapper<Classification> {

        @Override
        public Classification mapRow(ResultSet rs, int i) throws SQLException {
            Classification classification = new Classification();
            classification.setId(rs.getInt("id"));
            classification.setName(rs.getString("name"));
            return classification;
        }
    }
}
