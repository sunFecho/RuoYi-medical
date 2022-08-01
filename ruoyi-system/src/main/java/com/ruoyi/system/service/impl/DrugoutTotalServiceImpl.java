package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DrugoutTotal;
import com.ruoyi.system.mapper.DrugoutTotalMapper;
import com.ruoyi.system.service.IDrugoutTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugoutTotalServiceImpl implements IDrugoutTotalService {

    @Autowired
    private DrugoutTotalMapper drugoutTotalMapper;

    @Override
    public List<DrugoutTotal> selectDrugoutTotalList(DrugoutTotal drugoutTotal) {
        return drugoutTotalMapper.selectDrugoutTotalList(drugoutTotal);
    }
}
