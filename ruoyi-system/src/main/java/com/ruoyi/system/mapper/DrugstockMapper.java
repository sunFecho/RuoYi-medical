package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Drugstock;

/**
 * 药品库存Mapper接口
 * 
 * @author sf
 * @date 2022-07-29
 */
public interface DrugstockMapper 
{
    /**
     * 查询药品库存
     * 
     * @param id 药品库存主键
     * @return 药品库存
     */
    public Drugstock selectDrugstockById(Long id);

    /**
     * 查询药品库存列表
     * 
     * @param drugstock 药品库存
     * @return 药品库存集合
     */
    public List<Drugstock> selectDrugstockList(Drugstock drugstock);

    /**
     * 新增药品库存
     * 
     * @param drugstock 药品库存
     * @return 结果
     */
    public int insertDrugstock(Drugstock drugstock);

    /**
     * 修改药品库存
     * 
     * @param drugstock 药品库存
     * @return 结果
     */
    public int updateDrugstock(Drugstock drugstock);

    /**
     * 删除药品库存
     * 
     * @param id 药品库存主键
     * @return 结果
     */
    public int deleteDrugstockById(Long id);

    /**
     * 批量删除药品库存
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugstockByIds(Long[] ids);

    List<Drugstock> selectDrugstockListLimit(Drugstock drugstock);
}
