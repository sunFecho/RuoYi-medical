package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.DruginTotal;
import com.ruoyi.system.domain.DrugoutTotal;
import com.ruoyi.system.service.IDruginTotalService;
import com.ruoyi.system.service.IDrugoutTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/system/drugoutdetail")
public class DrugoutTotalController extends BaseController {
    @Autowired
    private IDrugoutTotalService drugoutTotalService;

    @GetMapping("/listTotal")
    public TableDataInfo listAll(DrugoutTotal drugoutTotal)
    {
        startPage();
        List<DrugoutTotal> list = drugoutTotalService.selectDrugoutTotalList(drugoutTotal);
        return getDataTable(list);
    }
}
