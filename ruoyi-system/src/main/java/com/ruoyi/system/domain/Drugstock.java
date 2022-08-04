package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品库存对象 drugstock
 * 
 * @author sf
 * @date 2022-07-29
 */
public class Drugstock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 药品编码 */
    @Excel(name = "药品编码")
    private Long drugId;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugName;

    /** 拼音简写 */
    @Excel(name = "拼音简写")
    private String drugEngname;

    /** 药品类别 */
    @Excel(name = "药品类别")
    private String drugType;

    /** 包装规格 */
    @Excel(name = "包装规格")
    private String drugPackage;

    /** 当前库存 */
    @Excel(name = "当前库存")
    private Long drugCount;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal drugPrice;

    /** 包装单位 */
    @Excel(name = "包装单位")
    private String drugPackingunit;

    /** 批次号 */
    @Excel(name = "批次号")
    private String drugOrder;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drugValiddate;

    /** 生产企业 */
    @Excel(name = "生产企业")
    private String drugFactory;

    public Long getDrugCount() {
        return drugCount;
    }

    public String getDrugOrder() {
        return drugOrder;
    }

    public void setDrugOrder(String drugOrder) {
        this.drugOrder = drugOrder;
    }

    public void setDrugCount(Long drugCount) {
        this.drugCount = drugCount;
    }

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
    public void setDrugPackage(String drugPackage) 
    {
        this.drugPackage = drugPackage;
    }

    public String getDrugPackage() 
    {
        return drugPackage;
    }

    public void setDrugPrice(BigDecimal drugPrice) 
    {
        this.drugPrice = drugPrice;
    }

    public BigDecimal getDrugPrice() 
    {
        return drugPrice;
    }
    public void setDrugPackingunit(String drugPackingunit) 
    {
        this.drugPackingunit = drugPackingunit;
    }

    public String getDrugPackingunit() 
    {
        return drugPackingunit;
    }
    public void setDrugValiddate(Date drugValiddate) 
    {
        this.drugValiddate = drugValiddate;
    }

    public Date getDrugValiddate() 
    {
        return drugValiddate;
    }
    public void setDrugFactory(String drugFactory) 
    {
        this.drugFactory = drugFactory;
    }

    public String getDrugFactory() 
    {
        return drugFactory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("drugId", getDrugId())
            .append("drugName", getDrugName())
            .append("drugEngname", getDrugEngname())
            .append("drugType", getDrugType())
            .append("drugPackage", getDrugPackage())
            .append("drugCount", getDrugCount())
            .append("drugPrice", getDrugPrice())
            .append("drugPackingunit", getDrugPackingunit())
            .append("drugOrder", getDrugOrder())
            .append("drugValiddate", getDrugValiddate())
            .append("drugFactory", getDrugFactory())
            .toString();
    }
}
