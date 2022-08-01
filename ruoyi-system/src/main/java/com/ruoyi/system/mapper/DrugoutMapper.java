package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Drugout;
import com.ruoyi.system.domain.Drugoutdetail;

/**
 * 药品出库Mapper接口
 * 
 * @author sf
 * @date 2022-07-29
 */
public interface DrugoutMapper 
{
    /**
     * 查询药品出库
     * 
     * @param id 药品出库主键
     * @return 药品出库
     */
    public Drugout selectDrugoutById(Long id);

    /**
     * 查询药品出库列表
     * 
     * @param drugout 药品出库
     * @return 药品出库集合
     */
    public List<Drugout> selectDrugoutList(Drugout drugout);

    /**
     * 新增药品出库
     * 
     * @param drugout 药品出库
     * @return 结果
     */
    public int insertDrugout(Drugout drugout);

    /**
     * 修改药品出库
     * 
     * @param drugout 药品出库
     * @return 结果
     */
    public int updateDrugout(Drugout drugout);

    /**
     * 删除药品出库
     * 
     * @param id 药品出库主键
     * @return 结果
     */
    public int deleteDrugoutById(Long id);

    /**
     * 批量删除药品出库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugoutByIds(Long[] ids);

    /**
     * 批量删除出库详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugoutdetailByDrugoutIds(Long[] ids);
    
    /**
     * 批量新增出库详情
     * 
     * @param drugoutdetailList 出库详情列表
     * @return 结果
     */
    public int batchDrugoutdetail(List<Drugoutdetail> drugoutdetailList);
    

    /**
     * 通过药品出库主键删除出库详情信息
     * 
     * @param id 药品出库ID
     * @return 结果
     */
    public int deleteDrugoutdetailByDrugoutId(Long id);
}
