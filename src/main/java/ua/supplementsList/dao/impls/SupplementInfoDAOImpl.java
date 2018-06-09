package ua.supplementsList.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.supplementsList.dao.interfaces.SupplementInfoDAO;
import ua.supplementsList.models.SupplementInfo;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SupplementInfoDAOImpl implements SupplementInfoDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insertInfo(SupplementInfo info) {
        String sql = "INSERT INTO supplementInfo (name, contents, classification_id) " +
                "VALUES (:name, :contents, :classificationId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", info.getName());
        params.addValue("contents", info.getContents());
        params.addValue("classificationId", info.getClassificationId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public SupplementInfo getInfo(int id) {
        String sql = "SELECT * FROM supplementInfo WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new SupplementInfoMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateInfo(SupplementInfo info) {
        String sql = "UPDATE supplementInfo SET name=:name, contents=:contents, classification_id=:classificationId WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", info.getName());
        params.addValue("contents", info.getContents());
        params.addValue("classificationId", info.getClassificationId());
        params.addValue("id", info.getId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeInfo(int id) {
        String sql = "DELETE FROM supplementInfo WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    private static final class SupplementInfoMapper implements RowMapper<SupplementInfo> {

        @Override
        public SupplementInfo mapRow(ResultSet rs, int i) throws SQLException {
            SupplementInfo info = new SupplementInfo();
            info.setId(rs.getInt("id"));
            info.setName(rs.getString("name"));
            info.setContents(rs.getString("contents"));
            info.setClassificationId(rs.getInt("classification_id"));
            return info;
        }
    }
}
