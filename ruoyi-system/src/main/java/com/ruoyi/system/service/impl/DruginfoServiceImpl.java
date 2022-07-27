package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DruginfoMapper;
import com.ruoyi.system.domain.Druginfo;
import com.ruoyi.system.service.IDruginfoService;

/**
 * 药品信息Service业务层处理
 * 
 * @author sf
 * @date 2022-07-27
 */
@Service
public class DruginfoServiceImpl implements IDruginfoService 
{
    @Autowired
    private DruginfoMapper druginfoMapper;

    /**
     * 查询药品信息
     * 
     * @param id 药品信息主键
     * @return 药品信息
     */
    @Override
    public Druginfo selectDruginfoById(Long id)
    {
        return druginfoMapper.selectDruginfoById(id);
    }

    /**
     * 查询药品信息列表
     * 
     * @param druginfo 药品信息
     * @return 药品信息
     */
    @Override
    public List<Druginfo> selectDruginfoList(Druginfo druginfo)
    {
        return druginfoMapper.selectDruginfoList(druginfo);
    }

    /**
     * 新增药品信息
     * 
     * @param druginfo 药品信息
     * @return 结果
     */
    @Override
    public int insertDruginfo(Druginfo druginfo)
    {
        return druginfoMapper.insertDruginfo(druginfo);
    }

    /**
     * 修改药品信息
     * 
     * @param druginfo 药品信息
     * @return 结果
     */
    @Override
    public int updateDruginfo(Druginfo druginfo)
    {
        return druginfoMapper.updateDruginfo(druginfo);
    }

    /**
     * 批量删除药品信息
     * 
     * @param ids 需要删除的药品信息主键
     * @return 结果
     */
    @Override
    public int deleteDruginfoByIds(Long[] ids)
    {
        return druginfoMapper.deleteDruginfoByIds(ids);
    }

    /**
     * 删除药品信息信息
     * 
     * @param id 药品信息主键
     * @return 结果
     */
    @Override
    public int deleteDruginfoById(Long id)
    {
        return druginfoMapper.deleteDruginfoById(id);
    }
}
