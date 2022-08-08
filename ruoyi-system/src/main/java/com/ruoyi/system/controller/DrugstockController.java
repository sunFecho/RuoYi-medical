package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Drugstock;
import com.ruoyi.system.service.IDrugstockService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品库存Controller
 * 
 * @author sf
 * @date 2022-07-29
 */
@RestController
@RequestMapping("/system/drugstock")
public class DrugstockController extends BaseController
{
    @Autowired
    private IDrugstockService drugstockService;

    /**
     * 查询药品库存列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:list')")
    @GetMapping("/list")
    public TableDataInfo list(Drugstock drugstock)
    {
        startPage();
        List<Drugstock> list = drugstockService.selectDrugstockList(drugstock);
        return getDataTable(list);
    }

    /**
     * 导出药品库存列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:export')")
    @Log(title = "药品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Drugstock drugstock)
    {
        List<Drugstock> list = drugstockService.selectDrugstockList(drugstock);
        ExcelUtil<Drugstock> util = new ExcelUtil<Drugstock>(Drugstock.class);
        util.exportExcel(response, list, "药品库存数据");
    }

    /**
     * 获取药品库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(drugstockService.selectDrugstockById(id));
    }

    /**
     * 新增药品库存
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:add')")
    @Log(title = "药品库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Drugstock drugstock)
    {
        return toAjax(drugstockService.insertDrugstock(drugstock));
    }

    /**
     * 修改药品库存
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:edit')")
    @Log(title = "药品库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Drugstock drugstock)
    {
        return toAjax(drugstockService.updateDrugstock(drugstock));
    }

    /**
     * 删除药品库存
     */
    @PreAuthorize("@ss.hasPermi('system:drugstock:remove')")
    @Log(title = "药品库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(drugstockService.deleteDrugstockByIds(ids));
    }

    /**
     * 查询药品库存列表
     */
    @GetMapping("/listLimit")
    public TableDataInfo listlimit(Drugstock drugstock)
    {
        startPage();
        List<Drugstock> list = drugstockService.selectDrugstockListLimit(drugstock);
        return getDataTable(list);
    }

//    /**
//     * 检查药品库存
//     */
//    @PreAuthorize("@ss.hasPermi('system:drugstock:list')")
//    @GetMapping("/check")
//    public AjaxResult check(Drugstock drugstock,Long id)
//    {
//        System.out.println(drugstockService.selectDrugstockById(id).getDrugCount());
//        return AjaxResult.success("success");
//    }


}
