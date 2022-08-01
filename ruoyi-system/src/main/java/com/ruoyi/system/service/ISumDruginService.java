package com.ruoyi.system.service;

import com.ruoyi.system.domain.SumDrugin;

import java.util.List;

public interface ISumDruginService {
    List<SumDrugin> selectSum(SumDrugin sumDrugin);

    List<SumDrugin> selectSumout(SumDrugin sumDrugin);
}
