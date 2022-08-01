package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库详情对象 drugindetail
 *
 * @author sf
 * @date 2022-07-27
 */
public class Drugindetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 入库单号 */
    @Excel(name = "入库单号")
    private Long druginId;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugName;

    /** 包装规格 */
    @Excel(name = "包装规格")
    private String drugPackage;
    /** 包装规格 */
    @Excel(name = "单位")
    private String drugPackingunit;
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
    @Excel(name = "药品类别")
    private String drugType;

    @Excel(name="药瓶编号")
    private String drugId;

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugPackingunit() {
        return drugPackingunit;
    }

    public void setDrugPackingunit(String drugPackingunit) {
        this.drugPackingunit = drugPackingunit;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDruginId(Long druginId)
    {
        this.druginId = druginId;
    }

    public Long getDruginId()
    {
        return druginId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("druginId", getDruginId())
                .append("drugName", getDrugName())
                .append("drugPackage", getDrugPackage())
                .append("drugCount", getDrugCount())
                .append("drugPrice", getDrugPrice())
                .append("drugOrder", getDrugOrder())
                .append("drugValiddate", getDrugValiddate())
                .append("remark", getRemark())
                .append("drugType",getDrugType())
                .append("drugPackingunit",getDrugPackingunit())
                .append("drugId",getDrugId())
                .toString();
    }
}