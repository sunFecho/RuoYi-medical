package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DrugstockMapper;
import com.ruoyi.system.domain.Drugstock;
import com.ruoyi.system.service.IDrugstockService;

/**
 * 药品库存Service业务层处理
 * 
 * @author sf
 * @date 2022-07-29
 */
@Service
public class DrugstockServiceImpl implements IDrugstockService 
{
    @Autowired
    private DrugstockMapper drugstockMapper;

    /**
     * 查询药品库存
     * 
     * @param id 药品库存主键
     * @return 药品库存
     */
    @Override
    public Drugstock selectDrugstockById(Long id)
    {
        return drugstockMapper.selectDrugstockById(id);
    }

    /**
     * 查询药品库存列表
     * 
     * @param drugstock 药品库存
     * @return 药品库存
     */
    @Override
    public List<Drugstock> selectDrugstockList(Drugstock drugstock)
    {
        return drugstockMapper.selectDrugstockList(drugstock);
    }

    /**
     * 新增药品库存
     * 
     * @param drugstock 药品库存
     * @return 结果
     */
    @Override
    public int insertDrugstock(Drugstock drugstock)
    {
        return drugstockMapper.insertDrugstock(drugstock);
    }

    /**
     * 修改药品库存
     * 
     * @param drugstock 药品库存
     * @return 结果
     */
    @Override
    public int updateDrugstock(Drugstock drugstock)
    {
        return drugstockMapper.updateDrugstock(drugstock);
    }

    /**
     * 批量删除药品库存
     * 
     * @param ids 需要删除的药品库存主键
     * @return 结果
     */
    @Override
    public int deleteDrugstockByIds(Long[] ids)
    {
        return drugstockMapper.deleteDrugstockByIds(ids);
    }

    /**
     * 删除药品库存信息
     * 
     * @param id 药品库存主键
     * @return 结果
     */
    @Override
    public int deleteDrugstockById(Long id)
    {
        return drugstockMapper.deleteDrugstockById(id);
    }

    @Override
    public List<Drugstock> selectDrugstockListLimit(Drugstock drugstock) {
        return drugstockMapper.selectDrugstockListLimit(drugstock);
    }
}
