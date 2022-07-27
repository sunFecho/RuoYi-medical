package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品信息对象 druginfo
 * 
 * @author sf
 * @date 2022-07-27
 */
public class Druginfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 药品编号 */
    @Excel(name = "药品编号")
    private Long drugId;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugName;

    /** 拼音简写 */
    @Excel(name = "拼音简写")
    private String drugEngname;

    /** 药品分类 */
    @Excel(name = "药品分类")
    private String drugType;

    /** 毒理分类 */
    @Excel(name = "毒理分类")
    private String drugToxicology;

    /** 制剂类型 */
    @Excel(name = "制剂类型")
    private String drugFormulation;

    /** 包装单位 */
    @Excel(name = "包装单位")
    private String drugPackingunit;

    /** 基本剂量 */
    @Excel(name = "基本剂量")
    private BigDecimal drugDose;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String drugDoseunit;

    /** 基本数量 */
    @Excel(name = "基本数量")
    private Long drugNum;

    /** 数量单位 */
    @Excel(name = "数量单位")
    private String drugNumunit;

    /** 包装规格 */
    @Excel(name = "包装规格")
    private String drugPackage;

    /** 用法 */
    @Excel(name = "用法")
    private String drugUse;

    /** 库存下限 */
    @Excel(name = "库存下限")
    private Long drugDown;

    /** 库存上限 */
    @Excel(name = "库存上限")
    private Long drugUp;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String drugFactory;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 有效性 */
    @Excel(name = "有效性")
    private Long isvalid;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDrugId(Long drugId) 
    {
        this.drugId = drugId;
    }

    public Long getDrugId() 
    {
        return drugId;
    }
    public void setDrugName(String drugName) 
    {
        this.drugName = drugName;
    }

    public String getDrugName() 
    {
        return drugName;
    }
    public void setDrugEngname(String drugEngname) 
    {
        this.drugEngname = drugEngname;
    }

    public String getDrugEngname() 
    {
        return drugEngname;
    }
    public void setDrugType(String drugType) 
    {
        this.drugType = drugType;
    }

    public String getDrugType() 
    {
        return drugType;
    }
    public void setDrugToxicology(String drugToxicology) 
    {
        this.drugToxicology = drugToxicology;
    }

    public String getDrugToxicology() 
    {
        return drugToxicology;
    }
    public void setDrugFormulation(String drugFormulation) 
    {
        this.drugFormulation = drugFormulation;
    }

    public String getDrugFormulation() 
    {
        return drugFormulation;
    }
    public void setDrugPackingunit(String drugPackingunit) 
    {
        this.drugPackingunit = drugPackingunit;
    }

    public String getDrugPackingunit() 
    {
        return drugPackingunit;
    }
    public void setDrugDose(BigDecimal drugDose) 
    {
        this.drugDose = drugDose;
    }

    public BigDecimal getDrugDose() 
    {
        return drugDose;
    }
    public void setDrugDoseunit(String drugDoseunit) 
    {
        this.drugDoseunit = drugDoseunit;
    }

    public String getDrugDoseunit() 
    {
        return drugDoseunit;
    }
    public void setDrugNum(Long drugNum) 
    {
        this.drugNum = drugNum;
    }

    public Long getDrugNum() 
    {
        return drugNum;
    }
    public void setDrugNumunit(String drugNumunit) 
    {
        this.drugNumunit = drugNumunit;
    }

    public String getDrugNumunit() 
    {
        return drugNumunit;
    }
    public void setDrugPackage(String drugPackage) 
    {
        this.drugPackage = drugPackage;
    }

    public String getDrugPackage() 
    {
        return drugPackage;
    }
    public void setDrugUse(String drugUse) 
    {
        this.drugUse = drugUse;
    }

    public String getDrugUse() 
    {
        return drugUse;
    }
    public void setDrugDown(Long drugDown) 
    {
        this.drugDown = drugDown;
    }

    public Long getDrugDown() 
    {
        return drugDown;
    }
    public void setDrugUp(Long drugUp) 
    {
        this.drugUp = drugUp;
    }

    public Long getDrugUp() 
    {
        return drugUp;
    }
    public void setDrugFactory(String drugFactory) 
    {
        this.drugFactory = drugFactory;
    }

    public String getDrugFactory() 
    {
        return drugFactory;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsvalid(Long isvalid) 
    {
        this.isvalid = isvalid;
    }

    public Long getIsvalid() 
    {
        return isvalid;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("drugId", getDrugId())
            .append("drugName", getDrugName())
            .append("drugEngname", getDrugEngname())
            .append("drugType", getDrugType())
            .append("drugToxicology", getDrugToxicology())
            .append("drugFormulation", getDrugFormulation())
            .append("drugPackingunit", getDrugPackingunit())
            .append("drugDose", getDrugDose())
            .append("drugDoseunit", getDrugDoseunit())
            .append("drugNum", getDrugNum())
            .append("drugNumunit", getDrugNumunit())
            .append("drugPackage", getDrugPackage())
            .append("drugUse", getDrugUse())
            .append("drugDown", getDrugDown())
            .append("drugUp", getDrugUp())
            .append("drugFactory", getDrugFactory())
            .append("sort", getSort())
            .append("isvalid", getIsvalid())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
