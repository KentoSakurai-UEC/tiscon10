package com.tiscon10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tiscon10.service.AnalysisResult;
import com.tiscon10.service.AnalysisService;


/**
 * 利用者データ分析のコントローラークラス。
 *
 * @author TIS Taro
 */

@Controller
public class AnalysisController {
    
    /** 利用者データ分析サービス */
    @Autowired
    private AnalysisService analysisService;

    /**
     * "/analysis"にGETリクエストが送信されたときのエンドポイント。
     * 画面表示に必要な情報を用意し、データ分析画面に遷移する。
     *
     * @param model 遷移先に連携するデータ
     * @return 遷移先画面ファイル名
     */
    @GetMapping("analysis")
    String analysis(Model model) {
        AnalysisResult.AgeGroupResult analysisResult = analysisService.getNumberOfUsersByAgeGroup();
        model.addAttribute("ageGroupData", analysisResult.ageGroupData());
        return "analysis";
    }
    
}
