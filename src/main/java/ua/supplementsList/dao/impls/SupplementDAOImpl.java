package ua.supplementsList.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.supplementsList.dao.interfaces.SupplementDAO;
import ua.supplementsList.models.Supplement;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SupplementDAOImpl implements SupplementDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insertSupplement(Supplement supplement) {
        String sql = "INSERT INTO supplements (code, info_id) VALUES (:code, :infoId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", supplement.getCode());
        params.addValue("infoId", supplement.getInfoId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Supplement getSupplement(int id) {
        String sql = "SELECT * FROM supplements WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new SupplementMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateSupplement(Supplement supplement) {
        String sql = "UPDATE supplements SET code=:code WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", supplement.getId());
        params.addValue("code", supplement.getCode());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeSupplement(int id) {
        String sql = "DELETE FROM supplements WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    private static final class SupplementMapper implements RowMapper<Supplement> {

        @Override
        public Supplement mapRow(ResultSet rs, int i) throws SQLException {
            Supplement supplement = new Supplement();
            supplement.setId(rs.getInt("id"));
            supplement.setCode(rs.getString("code"));
            supplement.setInfoId(rs.getInt("info_id"));
            return supplement;
        }
    }
}