package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Drugin;
import com.ruoyi.system.domain.Drugindetail;

/**
 * 药品入库Mapper接口
 * 
 * @author sf
 * @date 2022-07-27
 */
public interface DruginMapper 
{
    /**
     * 查询药品入库
     * 
     * @param id 药品入库主键
     * @return 药品入库
     */
    public Drugin selectDruginById(Long id);

    /**
     * 查询药品入库列表
     * 
     * @param drugin 药品入库
     * @return 药品入库集合
     */
    public List<Drugin> selectDruginList(Drugin drugin);

    /**
     * 新增药品入库
     * 
     * @param drugin 药品入库
     * @return 结果
     */
    public int insertDrugin(Drugin drugin);

    /**
     * 修改药品入库
     * 
     * @param drugin 药品入库
     * @return 结果
     */
    public int updateDrugin(Drugin drugin);

    /**
     * 删除药品入库
     * 
     * @param id 药品入库主键
     * @return 结果
     */
    public int deleteDruginById(Long id);

    /**
     * 批量删除药品入库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDruginByIds(Long[] ids);

    /**
     * 批量删除入库详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugindetailByDruginIds(Long[] ids);
    
    /**
     * 批量新增入库详情
     * 
     * @param drugindetailList 入库详情列表
     * @return 结果
     */
    public int batchDrugindetail(List<Drugindetail> drugindetailList);
    

    /**
     * 通过药品入库主键删除入库详情信息
     * 
     * @param id 药品入库ID
     * @return 结果
     */
    public int deleteDrugindetailByDruginId(Long id);
}
