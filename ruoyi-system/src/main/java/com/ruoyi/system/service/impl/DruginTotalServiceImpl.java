package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DruginTotal;
import com.ruoyi.system.mapper.DruginTotalMapper;
import com.ruoyi.system.service.IDruginTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DruginTotalServiceImpl implements IDruginTotalService {

    @Autowired
    private DruginTotalMapper druginTotalMapper;

    @Override
    public List<DruginTotal> selectDruginTotalList(DruginTotal druginTotal) {
        return druginTotalMapper.selectDruginTotalList(druginTotal);
    }
}
