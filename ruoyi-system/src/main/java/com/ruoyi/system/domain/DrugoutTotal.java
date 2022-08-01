package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class DrugoutTotal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 出库状态
     */
    @Excel(name = "出库状态")
    private String outStatus;

    /**
     * 出库类型
     */
    @Excel(name = "出库类型")
    private String outType;

    /**
     * 出库单号
     */
    @Excel(name = "出库单号")
    private Long outOrderid;

    /**
     * 出库日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outDate;

    /**
     * 有效性
     */
    @Excel(name = "有效性")
    private Long isvalid;
    @Excel(name = "出库单号")
    private Long drugoutId;

    /**
     * 药品名称
     */
    @Excel(name = "药品名称")
    private String drugName;

    /**
     * 包装规格
     */
    @Excel(name = "包装规格")
    private String drugPackage;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Long drugCount;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private BigDecimal drugPrice;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String drugOrder;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drugValiddate;

    /**
     * 药品类型
     */
    @Excel(name = "药品类型")
    private String drugType;

    /**
     * 药品编号
     */
    @Excel(name = "药品编号")
    private Long drugId;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String drugPackingunit;
    @Excel(name = "remark")
    private String remarkDetail;
    @Excel(name = "总价")
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public Long getOutOrderid() {
        return outOrderid;
    }

    public void setOutOrderid(Long outOrderid) {
        this.outOrderid = outOrderid;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Long getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Long isvalid) {
        this.isvalid = isvalid;
    }

    public Long getDrugoutId() {
        return drugoutId;
    }

    public void setDrugoutId(Long drugoutId) {
        this.drugoutId = drugoutId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugPackage() {
        return drugPackage;
    }

    public void setDrugPackage(String drugPackage) {
        this.drugPackage = drugPackage;
    }

    public Long getDrugCount() {
        return drugCount;
    }

    public void setDrugCount(Long drugCount) {
        this.drugCount = drugCount;
    }

    public BigDecimal getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(BigDecimal drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getDrugOrder() {
        return drugOrder;
    }

    public void setDrugOrder(String drugOrder) {
        this.drugOrder = drugOrder;
    }

    public Date getDrugValiddate() {
        return drugValiddate;
    }

    public void setDrugValiddate(Date drugValiddate) {
        this.drugValiddate = drugValiddate;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public String getDrugPackingunit() {
        return drugPackingunit;
    }

    public void setDrugPackingunit(String drugPackingunit) {
        this.drugPackingunit = drugPackingunit;
    }

    public String getRemarkDetail() {
        return remarkDetail;
    }

    public void setRemarkDetail(String remarkDetail) {
        this.remarkDetail = remarkDetail;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)

                .append("drugoutId", getDrugoutId())
                .append("drugName", getDrugName())
                .append("drugPackage", getDrugPackage())
                .append("drugCount", getDrugCount())
                .append("drugPrice", getDrugPrice())
                .append("drugOrder", getDrugOrder())
                .append("drugValiddate", getDrugValiddate())
                .append("drugPackingunit", getDrugPackingunit())
                .append("remark", getRemark())

                .append("id", getId())
                .append("remarkDetail", getRemarkDetail())
                .append("drugType", getDrugType())
                .append("outStatus", getOutStatus())
                .append("outType", getOutType())
                .append("outOrderid", getOutOrderid())
                .append("outDate", getOutDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("isvalid", getIsvalid())
                .append("total", getTotal())
                .append("drugId", getDrugoutId())
                .toString();
    }

}


