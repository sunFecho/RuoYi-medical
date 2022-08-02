package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Drugin;

/**
 * 药品入库Service接口
 * 
 * @author sf
 * @date 2022-07-27
 */
public interface IDruginService 
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
     * 批量删除药品入库
     * 
     * @param ids 需要删除的药品入库主键集合
     * @return 结果
     */
    public int deleteDruginByIds(Long[] ids);

    /**
     * 删除药品入库信息
     * 
     * @param id 药品入库主键
     * @return 结果
     */
    public int deleteDruginById(Long id);

    int changeStatusByid(Long id);
}
