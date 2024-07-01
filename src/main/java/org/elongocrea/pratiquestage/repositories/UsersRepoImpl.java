package org.elongocrea.pratiquestage.repositories;

import org.apache.commons.lang3.StringUtils;
import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class UsersRepoImpl implements UsersRepoCustom {

    @Autowired
    private JdbcTemplate jdbcTempl;

    @Autowired
    private AppUtils utils;

    private static final ResultSetExtractorImpl<Users> rsExtr = JdbcTemplateMapperFactory.newInstance()
            .addKeys("id")
            .ignorePropertyNotFound()
            .newResultSetExtractor(Users.class);

    //
    @Override
    public boolean isUnique(int id, String email) {
        final String sql = "SELECT NOT EXISTS("
                + "SELECT id FROM users WHERE id<>? AND email=? LIMIT 1"
                + ")";

        final Object[] params = new Object[] { id, email };

        return jdbcTempl.queryForObject(sql, Boolean.class, params);
    }

    @Override
    public Users getById(int id) {
        final String sql = "SELECT * FROM users WHERE id=?";

        final Object[] params = new Object[] { id };

        return utils.convertToObject(jdbcTempl.query(sql, rsExtr, params));
    }

    @Override
    public List<Users> get(boolean is_active, boolean is_block, boolean is_connected, String keyword) {
        final String _stmt = "SELECT * FROM users ";

        final String _order = " ORDER BY id";

        String sql = _stmt.concat("WHERE is_active=? AND is_block=? " +
                "AND is_connected=? AND last_connected AND username LIKE ?").concat(_order);

        final String _keyword = utils.getFilterKeyword(keyword);

        Object[] params =  new Object[] { is_active, is_block, is_connected, _keyword };

        if (StringUtils.isEmpty(keyword)) {
            sql = _stmt.concat("WHERE is_active=? AND is_block=? " +
                    "AND is_connected=? AND last_connected").concat(_order);

            params = new Object[] { is_active, is_block, is_connected };
        }

        return jdbcTempl.query(sql, rsExtr, params);
    }
}
