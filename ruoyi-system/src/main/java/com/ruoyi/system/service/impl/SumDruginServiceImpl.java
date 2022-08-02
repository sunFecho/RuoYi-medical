package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SumDrugin;
import com.ruoyi.system.mapper.SumDruginMapper;
import com.ruoyi.system.service.ISumDruginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SumDruginServiceImpl implements ISumDruginService {

    @Autowired
    private SumDruginMapper sumDruginMapper;
    @Override
    public List<SumDrugin> selectSum(SumDrugin sumDrugin) {
        return sumDruginMapper.selectSum(sumDrugin);
    }

    @Override
    public List<SumDrugin> selectSumout(SumDrugin sumDrugin) {
        return sumDruginMapper.selectSumout(sumDrugin);
    }

    @Override
    public List<SumDrugin> selectStockSum(SumDrugin sumDrugin) {
        return sumDruginMapper.selectStockSum(sumDrugin);
    }
}
