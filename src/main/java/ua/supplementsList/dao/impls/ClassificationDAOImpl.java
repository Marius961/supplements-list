package ua.supplementsList.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    public void insertClassification(Classification classification) {
        String sql = "INSERT INTO classifications (name) VALUES (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", classification.getName());
        jdbcTemplate.update(sql, params);
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
