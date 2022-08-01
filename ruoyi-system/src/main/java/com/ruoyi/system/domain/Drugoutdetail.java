package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出库详情对象 drugoutdetail
 * 
 * @author sf
 * @date 2022-07-29
 */
public class Drugoutdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private Long drugoutId;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugName;

    /** 包装规格 */
    @Excel(name = "包装规格")
    private String drugPackage;

    /** 数量 */
    @Excel(name = "数量")
    private Long drugCount;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal drugPrice;

    /** 批次号 */
    @Excel(name = "批次号")
    private String drugOrder;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drugValiddate;

    /** 药品类型 */
    @Excel(name = "药品类型")
    private String drugType;

    /** 药品编号 */
    @Excel(name = "药品编号")
    private Long drugId;

    /** 单位 */
    @Excel(name = "单位")
    private String drugPackingunit;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDrugoutId(Long drugoutId) 
    {
        this.drugoutId = drugoutId;
    }

    public Long getDrugoutId() 
    {
        return drugoutId;
    }
    public void setDrugName(String drugName) 
    {
        this.drugName = drugName;
    }

    public String getDrugName() 
    {
        return drugName;
    }
    public void setDrugPackage(String drugPackage) 
    {
        this.drugPackage = drugPackage;
    }

    public String getDrugPackage() 
    {
        return drugPackage;
    }
    public void setDrugCount(Long drugCount) 
    {
        this.drugCount = drugCount;
    }

    public Long getDrugCount() 
    {
        return drugCount;
    }
    public void setDrugPrice(BigDecimal drugPrice) 
    {
        this.drugPrice = drugPrice;
    }

    public BigDecimal getDrugPrice() 
    {
        return drugPrice;
    }
    public void setDrugOrder(String drugOrder) 
    {
        this.drugOrder = drugOrder;
    }

    public String getDrugOrder() 
    {
        return drugOrder;
    }
    public void setDrugValiddate(Date drugValiddate) 
    {
        this.drugValiddate = drugValiddate;
    }

    public Date getDrugValiddate() 
    {
        return drugValiddate;
    }
    public void setDrugType(String drugType) 
    {
        this.drugType = drugType;
    }

    public String getDrugType() 
    {
        return drugType;
    }
    public void setDrugId(Long drugId) 
    {
        this.drugId = drugId;
    }

    public Long getDrugId() 
    {
        return drugId;
    }
    public void setDrugPackingunit(String drugPackingunit) 
    {
        this.drugPackingunit = drugPackingunit;
    }

    public String getDrugPackingunit() 
    {
        return drugPackingunit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("drugoutId", getDrugoutId())
            .append("drugName", getDrugName())
            .append("drugPackage", getDrugPackage())
            .append("drugCount", getDrugCount())
            .append("drugPrice", getDrugPrice())
            .append("drugOrder", getDrugOrder())
            .append("drugValiddate", getDrugValiddate())
            .append("remark", getRemark())
            .append("drugType", getDrugType())
            .append("drugId", getDrugId())
            .append("drugPackingunit", getDrugPackingunit())
            .toString();
    }
}
