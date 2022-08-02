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
import com.ruoyi.system.domain.Drugin;
import com.ruoyi.system.service.IDruginService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品入库Controller
 * 
 * @author sf
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/system/drugin")
public class DruginController extends BaseController
{
    @Autowired
    private IDruginService druginService;

    /**
     * 查询药品入库列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:list')")
    @GetMapping("/list")
    public TableDataInfo list(Drugin drugin)
    {
        startPage();
        List<Drugin> list = druginService.selectDruginList(drugin);
        return getDataTable(list);
    }

    /**
     * 导出药品入库列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:export')")
    @Log(title = "药品入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Drugin drugin)
    {
        List<Drugin> list = druginService.selectDruginList(drugin);
        ExcelUtil<Drugin> util = new ExcelUtil<Drugin>(Drugin.class);
        util.exportExcel(response, list, "药品入库数据");
    }

    /**
     * 获取药品入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(druginService.selectDruginById(id));
    }

    /**
     * 新增药品入库
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:add')")
    @Log(title = "药品入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Drugin drugin)
    {
        return toAjax(druginService.insertDrugin(drugin));
    }

    /**
     * 修改药品入库
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:edit')")
    @Log(title = "药品入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Drugin drugin)
    {
        return toAjax(druginService.updateDrugin(drugin));
    }

    /**
     * 删除药品入库
     */
    @PreAuthorize("@ss.hasPermi('system:drugin:remove')")
    @Log(title = "药品入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(druginService.deleteDruginByIds(ids));
    }

    @GetMapping("/editStatusFinish/{id}")
    public AjaxResult changeStatus(@PathVariable("id") Long id)
    {
        return toAjax(druginService.changeStatusByid(id));
    }
}
