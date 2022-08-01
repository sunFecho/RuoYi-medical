package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Drugindetail;

/**
 * 入库详情Mapper接口
 * 
 * @author sf
 * @date 2022-07-27
 */
public interface DrugindetailMapper 
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
     * 删除入库详情
     * 
     * @param id 入库详情主键
     * @return 结果
     */
    public int deleteDrugindetailById(Long id);

    /**
     * 批量删除入库详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugindetailByIds(Long[] ids);
}
