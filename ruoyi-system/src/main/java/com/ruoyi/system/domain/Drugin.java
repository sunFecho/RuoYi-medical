package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品入库对象 drugin
 *
 * @author sf
 * @date 2022-07-27
 */
public class Drugin extends BaseEntity
{
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

    /** 入库详情信息 */
    private List<Drugindetail> drugindetailList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setInStatus(String inStatus)
    {
        this.inStatus = inStatus;
    }

    public String getInStatus()
    {
        return inStatus;
    }
    public void setInType(String inType)
    {
        this.inType = inType;
    }

    public String getInType()
    {
        return inType;
    }
    public void setInOrderid(Long inOrderid)
    {
        this.inOrderid = inOrderid;
    }

    public Long getInOrderid()
    {
        return inOrderid;
    }
    public void setInDate(Date inDate)
    {
        this.inDate = inDate;
    }

    public Date getInDate()
    {
        return inDate;
    }
    public void setIsvalid(Long isvalid)
    {
        this.isvalid = isvalid;
    }

    public Long getIsvalid()
    {
        return isvalid;
    }

    public List<Drugindetail> getDrugindetailList()
    {
        return drugindetailList;
    }

    public void setDrugindetailList(List<Drugindetail> drugindetailList)
    {
        this.drugindetailList = drugindetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("inStatus", getInStatus())
                .append("inType", getInType())
                .append("inOrderid", getInOrderid())
                .append("inDate", getInDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("isvalid", getIsvalid())
                .append("remark", getRemark())
                .append("drugindetailList", getDrugindetailList())
                .toString();
    }
}