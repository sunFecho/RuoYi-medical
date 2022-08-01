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
import com.ruoyi.system.domain.Drugindetail;
import com.ruoyi.system.service.IDrugindetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库详情Controller
 * 
 * @author sf
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/system/drugindetail")
public class DrugindetailController extends BaseController
{
    @Autowired
    private IDrugindetailService drugindetailService;

    /**
     * 查询入库详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(Drugindetail drugindetail)
    {
        startPage();
        List<Drugindetail> list = drugindetailService.selectDrugindetailList(drugindetail);
        return getDataTable(list);
    }

    /**
     * 导出入库详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:export')")
    @Log(title = "入库详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Drugindetail drugindetail)
    {
        List<Drugindetail> list = drugindetailService.selectDrugindetailList(drugindetail);
        ExcelUtil<Drugindetail> util = new ExcelUtil<Drugindetail>(Drugindetail.class);
        util.exportExcel(response, list, "入库详情数据");
    }

    /**
     * 获取入库详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(drugindetailService.selectDrugindetailById(id));
    }

    /**
     * 新增入库详情
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:add')")
    @Log(title = "入库详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Drugindetail drugindetail)
    {
        return toAjax(drugindetailService.insertDrugindetail(drugindetail));
    }

    /**
     * 修改入库详情
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:edit')")
    @Log(title = "入库详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Drugindetail drugindetail)
    {
        return toAjax(drugindetailService.updateDrugindetail(drugindetail));
    }

    /**
     * 删除入库详情
     */
    @PreAuthorize("@ss.hasPermi('system:drugindetail:remove')")
    @Log(title = "入库详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(drugindetailService.deleteDrugindetailByIds(ids));
    }
}
