package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SumDrugin;

import java.util.List;

public interface SumDruginMapper {
    List<SumDrugin> selectSum(SumDrugin sumDrugin);

    List<SumDrugin> selectSumout(SumDrugin sumDrugin);

    List<SumDrugin> selectStockSum(SumDrugin sumDrugin);
}
