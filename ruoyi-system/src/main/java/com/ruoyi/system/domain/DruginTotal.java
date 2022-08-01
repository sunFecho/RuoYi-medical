package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class DruginTotal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 入库状态 */
    @Excel(name = "入库状态")
    private String inStatus;

    /** 入库类型 */
    @Excel(name = "入库类型")
    private String inType;

    /** 入库单号 */
    @Excel(name = "入库单号")
    private Long inOrderid;

    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inDate;

    /** 有效性 */
    @Excel(name = "有效性")
    private Long isvalid;

    @Excel(name = "入库单号")
    private Long druginId;

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
    @Excel(name = "药品类别")
    private String drugType;

    @Excel(name = "备注")
    private String remarkDetail;
    @Excel(name = "单位")
    private String drugPackingunit;
    @Excel(name = "总价")
    private String total;
    @Excel(name="药瓶编号")
    private String drugId;

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInStatus() {
        return inStatus;
    }

    public void setInStatus(String inStatus) {
        this.inStatus = inStatus;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public Long getInOrderid() {
        return inOrderid;
    }

    public void setInOrderid(Long inOrderid) {
        this.inOrderid = inOrderid;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Long getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Long isvalid) {
        this.isvalid = isvalid;
    }

    public Long getDruginId() {
        return druginId;
    }

    public void setDruginId(Long druginId) {
        this.druginId = druginId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)

                .append("druginId", getDruginId())
                .append("drugName", getDrugName())
                .append("drugPackage", getDrugPackage())
                .append("drugCount", getDrugCount())
                .append("drugPrice", getDrugPrice())
                .append("drugOrder", getDrugOrder())
                .append("drugValiddate", getDrugValiddate())
                .append("drugPackingunit",getDrugPackingunit())
                .append("remark", getRemark())

                .append("id", getId())
                .append("remarkDetail", getRemarkDetail())
                .append("drugType",getDrugType())
                .append("inStatus", getInStatus())
                .append("inType", getInType())
                .append("inOrderid", getInOrderid())
                .append("inDate", getInDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("isvalid", getIsvalid())
                .append("total",getTotal())
                .append("drugId",getDruginId())
                .toString();
    }
}
