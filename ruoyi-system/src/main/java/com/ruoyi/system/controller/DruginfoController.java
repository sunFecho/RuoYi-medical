package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.system.domain.Druginfo;
import com.ruoyi.system.service.IDruginfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品信息Controller
 * 
 * @author sf
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/system/druginfo")
public class DruginfoController extends BaseController
{
    @Autowired
    private IDruginfoService druginfoService;

    /**
     * 查询药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Druginfo druginfo)
    {
        startPage();
        List<Druginfo> list = druginfoService.selectDruginfoList(druginfo);
        return getDataTable(list);
    }

    /**
     * 导出药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:export')")
    @Log(title = "药品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Druginfo druginfo)
    {
        List<Druginfo> list = druginfoService.selectDruginfoList(druginfo);
        ExcelUtil<Druginfo> util = new ExcelUtil<Druginfo>(Druginfo.class);
        util.exportExcel(response, list, "药品信息数据");
    }

    /**
     * 获取药品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(druginfoService.selectDruginfoById(id));
    }

    /**
     * 新增药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:add')")
    @Log(title = "药品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Druginfo druginfo)
    {
        druginfo.setCreateBy(getUsername());
        druginfo.setCreateDate(DateUtils.getNowDate());

        return toAjax(druginfoService.insertDruginfo(druginfo));
    }

    /**
     * 修改药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:edit')")
    @Log(title = "药品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Druginfo druginfo)
    {
        druginfo.setUpdateBy(getUsername());
        druginfo.setUpdateDate(DateUtils.getNowDate());
        return toAjax(druginfoService.updateDruginfo(druginfo));
    }

    /**
     * 删除药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:druginfo:remove')")
    @Log(title = "药品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(druginfoService.deleteDruginfoByIds(ids));
    }




}
