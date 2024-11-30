package com.tiscon10.service;

import java.util.List;

/**
 * データ分析結果を表すクラス。
 * 各分析対象ごとに内部レコードを定義。
 * 
 */
public class AnalysisResult {

    /**
     * 年代別分析結果を表すクラス。
     *
     * @param ageGroupData 年代別の利用者数データ
     */
    public static record AgeGroupResult(
        List<AgeGroupData> ageGroupData // 年代別の利用者数データ
    ) {
        /**
         * 年代別の利用者数を表す内部クラス。
         */
        public static record AgeGroupData(
            int ageGroup,   // 年代（例: 20, 30, 40...）
            int userCount   // 利用者数
        ) {}
    }

    /**
     * 保険種別分析結果を表すクラス。
     *
     * @param insuranceTypeData 保険種別の利用者数データ
     */
    public static record InsuranceTypeResult(
        List<InsuranceTypeData> insuranceTypeData // 保険種別の利用者数データ
    ) {
        /**
         * 保険種別の利用者数を表す内部クラス。
         */
        public static record InsuranceTypeData(
            int insuranceType, // 保険種別（例: 医療保険: 1, がん保険: 2...）
            int userCount      // 利用者数
        ) {}
    }
}
