package com.tiscon10.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.tiscon10.domain.InsuranceOrder;

/**
 * 利用者データ分析機能においてDBとのやり取りを行うクラス。
 *
 * 
 */
@Component
public class AnalysisDao {
    /**
     * データベース・アクセスAPIである「JDBC」を使い、名前付きパラメータを用いてSQLを実行するクラス
     */
    @Autowired
    private NamedParameterJdbcTemplate parameterJDBCtemplate;

    /**
     * INSURANCE_ORDER テーブルからすべての保険申し込みデータを取得する。
     * データ分析用ページで使用するメソッド。
     *
     * @return 保険申し込み済みデータの List
     */
    public List<InsuranceOrder> getAllInsuranceOrders() {
        String sql = """
            SELECT 
                RECEIPT_NO, INSURANCE_TYPE, KANJI_NAME, KANA_NAME, DATE_OF_BIRTH, ADDRESS, 
                TEL, EMAIL_ADDRESS, MARRIED, JOB, INCOME, TREATED 
            FROM INSURANCE_ORDER
            """;
        return parameterJDBCtemplate.query(sql, DataClassRowMapper.newInstance(InsuranceOrder.class));
    }

    /**
     * INSURANCE_ORDER テーブルから年代別の利用者数を取得する。
     * データ分析用ページで使用するメソッド。
     *
     * @return 年代別利用者数の List（Map のリスト形式で返却）
     */
    public List<Map<String, Object>> getNumberOfUsersByAgeGroup() {
        String sql = """
            SELECT 
                FLOOR((YEAR(CURRENT_DATE) - YEAR(PARSEDATETIME(DATE_OF_BIRTH, 'yyyy/MM/dd'))) / 10) * 10 AS age_group,
                COUNT(*) AS user_count
            FROM INSURANCE_ORDER
            GROUP BY age_group
            ORDER BY age_group;
            """;
        return parameterJDBCtemplate.queryForList(sql, new MapSqlParameterSource());
    }

    /**
     * INSURANCE_ORDER テーブルから保険種別の利用者数を取得する。
     * データ分析用ページで使用するメソッド。
     *
     * @return 保険種別利用者数の List（Map のリスト形式で返却）
     */
    public List<Map<String, Object>> getNumberOfUsersByInsuranceType() {
        String sql = """
            SELECT 
                INSURANCE_TYPE, 
                COUNT(*) AS user_count 
            FROM 
                INSURANCE_ORDER 
            GROUP BY 
                INSURANCE_TYPE 
            ORDER BY 
                INSURANCE_TYPE;
            """;
        return parameterJDBCtemplate.queryForList(sql, new MapSqlParameterSource());
    }

    /**
     * INSURANCE_ORDER テーブルから都道府県別の利用者数を取得する。
     * データ分析用ページで使用するメソッド。
     *
     * @return 都道府県別利用者数の List（Map のリスト形式で返却）
     */
    public List<Map<String, Object>> getNumberOfUsersByPrefecture() {
        String sql = """
            SELECT 
                ADDRESS AS prefecture,
                COUNT(*) AS user_count
            FROM INSURANCE_ORDER
            GROUP BY ADDRESS
            ORDER BY user_count DESC;
        """;
        return parameterJDBCtemplate.queryForList(sql, new MapSqlParameterSource());
    }
}
