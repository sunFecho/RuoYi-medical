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
import com.ruoyi.system.domain.Drugout;
import com.ruoyi.system.service.IDrugoutService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品出库Controller
 * 
 * @author sf
 * @date 2022-07-29
 */
@RestController
@RequestMapping("/system/drugout")
public class DrugoutController extends BaseController
{
    @Autowired
    private IDrugoutService drugoutService;

    /**
     * 查询药品出库列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:list')")
    @GetMapping("/list")
    public TableDataInfo list(Drugout drugout)
    {
        startPage();
        List<Drugout> list = drugoutService.selectDrugoutList(drugout);
        return getDataTable(list);
    }

    /**
     * 导出药品出库列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:export')")
    @Log(title = "药品出库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Drugout drugout)
    {
        List<Drugout> list = drugoutService.selectDrugoutList(drugout);
        ExcelUtil<Drugout> util = new ExcelUtil<Drugout>(Drugout.class);
        util.exportExcel(response, list, "药品出库数据");
    }

    /**
     * 获取药品出库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(drugoutService.selectDrugoutById(id));
    }

    /**
     * 新增药品出库
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:add')")
    @Log(title = "药品出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Drugout drugout)
    {
        return toAjax(drugoutService.insertDrugout(drugout));
    }

    /**
     * 修改药品出库
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:edit')")
    @Log(title = "药品出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Drugout drugout)
    {
        return toAjax(drugoutService.updateDrugout(drugout));
    }

    /**
     * 删除药品出库
     */
    @PreAuthorize("@ss.hasPermi('system:drugout:remove')")
    @Log(title = "药品出库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(drugoutService.deleteDrugoutByIds(ids));
    }
}
