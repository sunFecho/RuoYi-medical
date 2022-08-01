package com.ruoyi.system.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.DruginTotal;
import com.ruoyi.system.service.IDruginTotalService;
import com.ruoyi.system.service.IDruginfoService;
import com.ruoyi.system.service.impl.DruginTotalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/system/drugindetail")
public class DruginTotalController extends BaseController{

    @Autowired
    private IDruginTotalService druginTotalService;
    @Autowired
    private IDruginfoService druginfoService;

    @GetMapping("/listTotal")
    public TableDataInfo listAll(DruginTotal druginTotal)
    {
        startPage();
        List<DruginTotal> list = druginTotalService.selectDruginTotalList(druginTotal);
        return getDataTable(list);
    }

//    @PreAuthorize("@ss.hasPermi('system:druginfo:query')")
//    @GetMapping(value = "/{drugId}")
//    public AjaxResult getInfoBydrugId(@PathVariable("drugId") Long drugId)
//    {
//        return AjaxResult.success(druginfoService.selectDruginfoBydrugId(drugId));
//    }
}