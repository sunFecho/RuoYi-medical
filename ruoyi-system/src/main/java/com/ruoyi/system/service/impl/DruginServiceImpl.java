package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.Drugindetail;
import com.ruoyi.system.mapper.DruginMapper;
import com.ruoyi.system.domain.Drugin;
import com.ruoyi.system.service.IDruginService;

/**
 * 药品入库Service业务层处理
 * 
 * @author sf
 * @date 2022-07-27
 */
@Service
public class DruginServiceImpl implements IDruginService 
{
    @Autowired
    private DruginMapper druginMapper;

    /**
     * 查询药品入库
     * 
     * @param id 药品入库主键
     * @return 药品入库
     */
    @Override
    public Drugin selectDruginById(Long id)
    {
        return druginMapper.selectDruginById(id);
    }

    /**
     * 查询药品入库列表
     * 
     * @param drugin 药品入库
     * @return 药品入库
     */
    @Override
    public List<Drugin> selectDruginList(Drugin drugin)
    {
        return druginMapper.selectDruginList(drugin);
    }

    /**
     * 新增药品入库
     * 
     * @param drugin 药品入库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDrugin(Drugin drugin)
    {
        drugin.setCreateTime(DateUtils.getNowDate());
        int rows = druginMapper.insertDrugin(drugin);
        insertDrugindetail(drugin);
        return rows;
    }

    /**
     * 修改药品入库
     * 
     * @param drugin 药品入库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDrugin(Drugin drugin)
    {
        drugin.setUpdateTime(DateUtils.getNowDate());
        druginMapper.deleteDrugindetailByDruginId(drugin.getId());
        insertDrugindetail(drugin);
        return druginMapper.updateDrugin(drugin);
    }

    /**
     * 批量删除药品入库
     * 
     * @param ids 需要删除的药品入库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDruginByIds(Long[] ids)
    {
        druginMapper.deleteDrugindetailByDruginIds(ids);
        return druginMapper.deleteDruginByIds(ids);
    }

    /**
     * 删除药品入库信息
     * 
     * @param id 药品入库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDruginById(Long id)
    {
        druginMapper.deleteDrugindetailByDruginId(id);
        return druginMapper.deleteDruginById(id);
    }

    /**
     * 新增入库详情信息
     * 
     * @param drugin 药品入库对象
     */
    public void insertDrugindetail(Drugin drugin)
    {
        List<Drugindetail> drugindetailList = drugin.getDrugindetailList();
        Long id = drugin.getId();
        if (StringUtils.isNotNull(drugindetailList))
        {
            List<Drugindetail> list = new ArrayList<Drugindetail>();
            for (Drugindetail drugindetail : drugindetailList)
            {
                drugindetail.setDruginId(id);
                list.add(drugindetail);
            }
            if (list.size() > 0)
            {
                druginMapper.batchDrugindetail(list);
            }
        }
    }
}
