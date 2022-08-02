package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Drugout;

/**
 * 药品出库Service接口
 * 
 * @author sf
 * @date 2022-07-29
 */
public interface IDrugoutService 
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
     * 批量删除药品出库
     * 
     * @param ids 需要删除的药品出库主键集合
     * @return 结果
     */
    public int deleteDrugoutByIds(Long[] ids);

    /**
     * 删除药品出库信息
     * 
     * @param id 药品出库主键
     * @return 结果
     */
    public int deleteDrugoutById(Long id);

    int changeStatusByid(Long id);
}
