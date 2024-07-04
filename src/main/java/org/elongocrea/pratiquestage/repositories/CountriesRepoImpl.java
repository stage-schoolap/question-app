package org.elongocrea.pratiquestage.repositories;

import org.apache.commons.lang3.StringUtils;
import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountriesRepoImpl implements CountriesRepoCustom{

    @Autowired
    private JdbcTemplate jdbcTempl;

    @Autowired
    private AppUtils utils;

    private static final ResultSetExtractorImpl<Countries> rsExtr = JdbcTemplateMapperFactory.newInstance()
            .addKeys("id")
            .ignorePropertyNotFound()
            .newResultSetExtractor(Countries.class);

    //
    @Override
    public boolean isUnique(int id, String name_fr) {
        final String sql = "SELECT NOT EXISTS("
                + "SELECT id FROM countries WHERE id<>? AND name_fr=? LIMIT 1"
                + ")";

        final Object[] params = new Object[] { id, name_fr };

        return jdbcTempl.queryForObject(sql, Boolean.class, params);
    }

    @Override
    public Countries getById(int id) {
        final String sql = "SELECT * FROM countries ";

        final Object[] params = new Object[] { id };

        return utils.convertToObject(jdbcTempl.query(sql, rsExtr, params));
    }

    @Override
    public List<Countries> get( String keyword) {
        final String _stmt = "SELECT * FROM countries";

        final String _order = " ORDER BY id";

        String sql = _stmt.concat("WHERE name_fr LIKE ?").concat(_order);

        final String _keyword = utils.getFilterKeyword(keyword);

        Object[] params = new Object[] { _keyword };

        return jdbcTempl.query(sql, rsExtr, params);
    }
}
