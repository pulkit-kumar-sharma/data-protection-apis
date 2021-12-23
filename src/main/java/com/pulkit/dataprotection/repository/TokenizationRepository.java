package com.pulkit.dataprotection.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class TokenizationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TokenizationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String ORIGINAL_VALUE = " original_value ";
    private static final String TOKENIZED_VALUE = " tokenized_value ";
    private static final String TABLE_NAME = " dp_tokenization_mapping ";

    private static final String GET_TOKENIZED_VALUE = "SELECT" + TOKENIZED_VALUE + "FROM" + TABLE_NAME + "WHERE" + ORIGINAL_VALUE + "=:" + ORIGINAL_VALUE.strip();
    private static final String GET_ORIGINAL_VALUE = "SELECT" + ORIGINAL_VALUE + "FROM" + TABLE_NAME + "WHERE" + TOKENIZED_VALUE + "=:" + TOKENIZED_VALUE.strip();

    private static final String GET_TOKENIZED_VALUE_RECORD_COUNT = "SELECT COUNT(*) FROM" + TABLE_NAME + "WHERE" + TOKENIZED_VALUE + "=:" + TOKENIZED_VALUE.strip();
    private static final String GET_ORIGINAL_VALUE_RECORD_COUNT = "SELECT COUNT(*) FROM" + TABLE_NAME + "WHERE" + ORIGINAL_VALUE + "=:" + ORIGINAL_VALUE.strip();

    private static final String INSERT_RECORD = "INSERT INTO" + TABLE_NAME + "(" + ORIGINAL_VALUE + "," + TOKENIZED_VALUE + ") VALUES (:" + ORIGINAL_VALUE.strip() + ",:" + TOKENIZED_VALUE.strip() + ")";

    public String getTokenizedValue(String inputString){
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(ORIGINAL_VALUE.strip(), inputString);
        return namedParameterJdbcTemplate.queryForObject(GET_TOKENIZED_VALUE,parameters,String.class);
    }

    public Integer getTokenizedValueRecordCount(String inputString){
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(TOKENIZED_VALUE.strip(), inputString);
        return namedParameterJdbcTemplate.queryForObject(GET_TOKENIZED_VALUE_RECORD_COUNT,parameters,Integer.class);
    }

    public String getOriginalValue(String inputString){
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(TOKENIZED_VALUE.strip(), inputString);
        return namedParameterJdbcTemplate.queryForObject(GET_ORIGINAL_VALUE,parameters,String.class);
    }

    public Integer getOriginalValueRecordCount(String inputString){
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(ORIGINAL_VALUE.strip(), inputString);
        return namedParameterJdbcTemplate.queryForObject(GET_ORIGINAL_VALUE_RECORD_COUNT,parameters,Integer.class);
    }

    public void insertRecord(String inputString, String tokenizedValue){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORIGINAL_VALUE.strip(),inputString);
        parameters.addValue(TOKENIZED_VALUE.strip(),tokenizedValue);
        namedParameterJdbcTemplate.update(INSERT_RECORD, parameters);
    }
}
