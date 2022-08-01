package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DrugindetailMapper;
import com.ruoyi.system.domain.Drugindetail;
import com.ruoyi.system.service.IDrugindetailService;

/**
 * 入库详情Service业务层处理
 * 
 * @author sf
 * @date 2022-07-27
 */
@Service
public class DrugindetailServiceImpl implements IDrugindetailService 
{
    @Autowired
    private DrugindetailMapper drugindetailMapper;

    /**
     * 查询入库详情
     * 
     * @param id 入库详情主键
     * @return 入库详情
     */
    @Override
    public Drugindetail selectDrugindetailById(Long id)
    {
        return drugindetailMapper.selectDrugindetailById(id);
    }

    /**
     * 查询入库详情列表
     * 
     * @param drugindetail 入库详情
     * @return 入库详情
     */
    @Override
    public List<Drugindetail> selectDrugindetailList(Drugindetail drugindetail)
    {
        return drugindetailMapper.selectDrugindetailList(drugindetail);
    }

    /**
     * 新增入库详情
     * 
     * @param drugindetail 入库详情
     * @return 结果
     */
    @Override
    public int insertDrugindetail(Drugindetail drugindetail)
    {
        return drugindetailMapper.insertDrugindetail(drugindetail);
    }

    /**
     * 修改入库详情
     * 
     * @param drugindetail 入库详情
     * @return 结果
     */
    @Override
    public int updateDrugindetail(Drugindetail drugindetail)
    {
        return drugindetailMapper.updateDrugindetail(drugindetail);
    }

    /**
     * 批量删除入库详情
     * 
     * @param ids 需要删除的入库详情主键
     * @return 结果
     */
    @Override
    public int deleteDrugindetailByIds(Long[] ids)
    {
        return drugindetailMapper.deleteDrugindetailByIds(ids);
    }

    /**
     * 删除入库详情信息
     * 
     * @param id 入库详情主键
     * @return 结果
     */
    @Override
    public int deleteDrugindetailById(Long id)
    {
        return drugindetailMapper.deleteDrugindetailById(id);
    }
}
