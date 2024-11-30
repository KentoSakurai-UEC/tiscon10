package com.tiscon10.viewhelper;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;

@Component
public class InsuranceTypeHelper implements com.github.jknack.handlebars.Helper<Integer> {

    @Override
    public Object apply(Integer insuranceType, Options options) {
        return switch (insuranceType) {
            case 1 -> "医療保険";
            case 2 -> "死亡保険";
            case 3 -> "がん保険";
            default -> "不明な保険";
        };
    }
}