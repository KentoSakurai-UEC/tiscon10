package com.tiscon10.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiscon10.dao.AnalysisDao;
import com.tiscon10.domain.InsuranceOrder;
/**
 * 利用者データ分析を担当するクラス。
 *
 * @param 
 */
@Service
public class AnalysisService {

    /**
     * データ分析DAO
     */
    @Autowired
    private AnalysisDao analysisDao;
    
    /**
     * INSURANCE_ORDER テーブルからすべての保険申し込みデータを取得する。
     * 
     *
     * @return 保険申し込み済みデータのリスト
     */
    public List<InsuranceOrder> getAllInsuranceOrders() {
        return analysisDao.getAllInsuranceOrders();
    }

    /**
     * INSURANCE_ORDER テーブルから年代別の利用者数を取得する。
     * 
     *
     * @return 年代別分析結果を表すクラス (AnalysisResult.AgeGroupResult)
     */
    public AnalysisResult.AgeGroupResult getNumberOfUsersByAgeGroup() {
        // SQL を実行し、年代別の利用者数を取得する。
        List<Map<String, Object>> numOfUsersByAgeGroup = analysisDao.getNumberOfUsersByAgeGroup();

        // DB 取得結果を AnalysisResult.AgeGroupData の List に変換
        List<AnalysisResult.AgeGroupResult.AgeGroupData> ageGroupDataList = new ArrayList<>();
        for (Map<String, Object> row : numOfUsersByAgeGroup) {
            int ageGroup = ((Number) row.get("age_group")).intValue(); // 年代 (クエリで指定したエイリアスを使用)
            int userCount = ((Number) row.get("user_count")).intValue(); // 利用者数 (クエリで指定したエイリアスを使用)
            ageGroupDataList.add(new AnalysisResult.AgeGroupResult.AgeGroupData(ageGroup, userCount));
        }
        // 年代別分析結果を返す。
        AnalysisResult.AgeGroupResult analysisResult = new AnalysisResult.AgeGroupResult(ageGroupDataList);

        return analysisResult;
    }

    /**
     * INSURANCE_ORDER テーブルから保険種別の利用者数を取得する。
     * 
     *
     * @return 保険種別分析結果を表すクラス (AnalysisResult.InsuranceTypeResult)
     */
    public AnalysisResult.InsuranceTypeResult getNumberOfUsersByInsuranceType() {
        // SQL を実行し、保険種別の利用者数を取得する。
        List<Map<String, Object>> InsuranceTypeGroupDataList = analysisDao.getNumberOfUsersByInsuranceType();

        // DB 取得結果を AnalysisResult.InsuranceTypeResult の List に変換
        List<AnalysisResult.InsuranceTypeResult.InsuranceTypeData> insuranceTypeDataList = new ArrayList<>();
        for (Map<String, Object> row : InsuranceTypeGroupDataList) {
            int insuranceType = ((Number) row.get("INSURANCE_TYPE")).intValue(); // 保険種別
            int userCount = ((Number) row.get("user_count")).intValue(); // 利用者数
            insuranceTypeDataList.add(new AnalysisResult.InsuranceTypeResult.InsuranceTypeData(insuranceType, userCount));
        }
        // 保険種別分析結果を返す。
        AnalysisResult.InsuranceTypeResult analysisResult = new AnalysisResult.InsuranceTypeResult(insuranceTypeDataList);

        return analysisResult;
    }
}
