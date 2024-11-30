package com.tiscon10.service;

import java.util.List;

/**
 * 年代別分析結果を表すクラス。
 *
 * @param ageGroupData 年代別の利用者数データ（List<Map形式で保持））
 */
public record AnalysisResult(
    List<AgeGroupData> ageGroupData  // 年代別の利用者数データ
) {

    /**
     * 年代別の利用者数を表す内部クラス。
     */
    public static record AgeGroupData(
        int ageGroup,       // 年代（例: 20, 30, 40...）
        int userCount       // 利用者数
    ) {}
}
