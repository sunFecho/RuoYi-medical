package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品出库对象 drugout
 * 
 * @author sf
 * @date 2022-07-29
 */
public class Drugout extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 出库状态 */
    @Excel(name = "出库状态")
    private String outStatus;

    /** 出库类型 */
    @Excel(name = "出库类型")
    private String outType;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private Long outOrderid;

    /** 出库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outDate;

    /** 有效性 */
    @Excel(name = "有效性")
    private Long isvalid;

    /** 出库详情信息 */
    private List<Drugoutdetail> drugoutdetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOutStatus(String outStatus) 
    {
        this.outStatus = outStatus;
    }

    public String getOutStatus() 
    {
        return outStatus;
    }
    public void setOutType(String outType) 
    {
        this.outType = outType;
    }

    public String getOutType() 
    {
        return outType;
    }
    public void setOutOrderid(Long outOrderid) 
    {
        this.outOrderid = outOrderid;
    }

    public Long getOutOrderid() 
    {
        return outOrderid;
    }
    public void setOutDate(Date outDate) 
    {
        this.outDate = outDate;
    }

    public Date getOutDate() 
    {
        return outDate;
    }
    public void setIsvalid(Long isvalid) 
    {
        this.isvalid = isvalid;
    }

    public Long getIsvalid() 
    {
        return isvalid;
    }

    public List<Drugoutdetail> getDrugoutdetailList()
    {
        return drugoutdetailList;
    }

    public void setDrugoutdetailList(List<Drugoutdetail> drugoutdetailList)
    {
        this.drugoutdetailList = drugoutdetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("outStatus", getOutStatus())
            .append("outType", getOutType())
            .append("outOrderid", getOutOrderid())
            .append("outDate", getOutDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("isvalid", getIsvalid())
            .append("remark", getRemark())
            .append("drugoutdetailList", getDrugoutdetailList())
            .toString();
    }
}
