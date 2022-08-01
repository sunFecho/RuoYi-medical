package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Drugindetail;

/**
 * 入库详情Service接口
 * 
 * @author sf
 * @date 2022-07-27
 */
public interface IDrugindetailService 
{
    /**
     * 查询入库详情
     * 
     * @param id 入库详情主键
     * @return 入库详情
     */
    public Drugindetail selectDrugindetailById(Long id);

    /**
     * 查询入库详情列表
     * 
     * @param drugindetail 入库详情
     * @return 入库详情集合
     */
    public List<Drugindetail> selectDrugindetailList(Drugindetail drugindetail);

    /**
     * 新增入库详情
     * 
     * @param drugindetail 入库详情
     * @return 结果
     */
    public int insertDrugindetail(Drugindetail drugindetail);

    /**
     * 修改入库详情
     * 
     * @param drugindetail 入库详情
     * @return 结果
     */
    public int updateDrugindetail(Drugindetail drugindetail);

    /**
     * 批量删除入库详情
     * 
     * @param ids 需要删除的入库详情主键集合
     * @return 结果
     */
    public int deleteDrugindetailByIds(Long[] ids);

    /**
     * 删除入库详情信息
     * 
     * @param id 入库详情主键
     * @return 结果
     */
    public int deleteDrugindetailById(Long id);
}
