package com.hhhhhx.mbgl.param.drug;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

@Data
public class DrugSearchParam extends BasePage {

    /**
     * 药品分类 中药 西药
     */
    private Integer classification;
    private String name;
}
