package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Druginfo;

/**
 * 药品信息Mapper接口
 * 
 * @author sf
 * @date 2022-07-27
 */
public interface DruginfoMapper 
{
    /**
     * 查询药品信息
     * 
     * @param id 药品信息主键
     * @return 药品信息
     */
    public Druginfo selectDruginfoById(Long id);

    /**
     * 查询药品信息列表
     * 
     * @param druginfo 药品信息
     * @return 药品信息集合
     */
    public List<Druginfo> selectDruginfoList(Druginfo druginfo);

    /**
     * 新增药品信息
     * 
     * @param druginfo 药品信息
     * @return 结果
     */
    public int insertDruginfo(Druginfo druginfo);

    /**
     * 修改药品信息
     * 
     * @param druginfo 药品信息
     * @return 结果
     */
    public int updateDruginfo(Druginfo druginfo);

    /**
     * 删除药品信息
     * 
     * @param id 药品信息主键
     * @return 结果
     */
    public int deleteDruginfoById(Long id);

    /**
     * 批量删除药品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDruginfoByIds(Long[] ids);

    Druginfo selectDruginfoBydrugId(Long drugId);
}
