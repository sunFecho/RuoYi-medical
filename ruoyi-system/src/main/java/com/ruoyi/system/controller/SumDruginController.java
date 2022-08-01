package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.DruginTotal;
import com.ruoyi.system.domain.SumDrugin;
import com.ruoyi.system.service.ISumDruginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/sumdrug")
public class SumDruginController extends BaseController {

    @Autowired
    private ISumDruginService sumDruginService;

    @GetMapping("/list")
    public TableDataInfo listAll(SumDrugin sumDrugin)
    {
        List<SumDrugin> list = sumDruginService.selectSum(sumDrugin);
        return getDataTable(list);
    }

    @GetMapping("/listSumout")
    public TableDataInfo listSumout(SumDrugin sumDrugin)
    {
        List<SumDrugin> list = sumDruginService.selectSumout(sumDrugin);
        return getDataTable(list);
    }

}
