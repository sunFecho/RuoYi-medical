package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

public class SumDrugin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private BigDecimal sumall;
    private Long sumcount;

    public BigDecimal getSumall() {
        return sumall;
    }

    public void setSumall(BigDecimal sumall) {
        this.sumall = sumall;
    }

    public Long getSumcount() {
        return sumcount;
    }

    public void setSumcount(Long sumcount) {
        this.sumcount = sumcount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sumall",getSumall())
                .append("sumcount",getSumcount())
                .toString();
    }
}
