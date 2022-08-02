package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.Drugoutdetail;
import com.ruoyi.system.mapper.DrugoutMapper;
import com.ruoyi.system.domain.Drugout;
import com.ruoyi.system.service.IDrugoutService;

/**
 * 药品出库Service业务层处理
 * 
 * @author sf
 * @date 2022-07-29
 */
@Service
public class DrugoutServiceImpl implements IDrugoutService 
{
    @Autowired
    private DrugoutMapper drugoutMapper;

    /**
     * 查询药品出库
     * 
     * @param id 药品出库主键
     * @return 药品出库
     */
    @Override
    public Drugout selectDrugoutById(Long id)
    {
        return drugoutMapper.selectDrugoutById(id);
    }

    /**
     * 查询药品出库列表
     * 
     * @param drugout 药品出库
     * @return 药品出库
     */
    @Override
    public List<Drugout> selectDrugoutList(Drugout drugout)
    {
        return drugoutMapper.selectDrugoutList(drugout);
    }

    /**
     * 新增药品出库
     * 
     * @param drugout 药品出库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDrugout(Drugout drugout)
    {
        drugout.setCreateTime(DateUtils.getNowDate());
        int rows = drugoutMapper.insertDrugout(drugout);
        insertDrugoutdetail(drugout);
        return rows;
    }

    /**
     * 修改药品出库
     * 
     * @param drugout 药品出库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDrugout(Drugout drugout)
    {
        drugout.setUpdateTime(DateUtils.getNowDate());
        drugoutMapper.deleteDrugoutdetailByDrugoutId(drugout.getId());
        insertDrugoutdetail(drugout);
        return drugoutMapper.updateDrugout(drugout);
    }

    /**
     * 批量删除药品出库
     * 
     * @param ids 需要删除的药品出库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDrugoutByIds(Long[] ids)
    {
        drugoutMapper.deleteDrugoutdetailByDrugoutIds(ids);
        return drugoutMapper.deleteDrugoutByIds(ids);
    }

    /**
     * 删除药品出库信息
     * 
     * @param id 药品出库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDrugoutById(Long id)
    {
        drugoutMapper.deleteDrugoutdetailByDrugoutId(id);
        return drugoutMapper.deleteDrugoutById(id);
    }

    @Override
    public int changeStatusByid(Long id) {
        return drugoutMapper.changeStatusByid(id);
    }

    /**
     * 新增出库详情信息
     * 
     * @param drugout 药品出库对象
     */
    public void insertDrugoutdetail(Drugout drugout)
    {
        List<Drugoutdetail> drugoutdetailList = drugout.getDrugoutdetailList();
        Long id = drugout.getId();
        if (StringUtils.isNotNull(drugoutdetailList))
        {
            List<Drugoutdetail> list = new ArrayList<Drugoutdetail>();
            for (Drugoutdetail drugoutdetail : drugoutdetailList)
            {
                drugoutdetail.setDrugoutId(id);
                list.add(drugoutdetail);
            }
            if (list.size() > 0)
            {
                drugoutMapper.batchDrugoutdetail(list);
            }
        }
    }
}
