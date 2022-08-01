package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DruginTotal;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface DruginTotalMapper {
    List<DruginTotal> selectDruginTotalList(DruginTotal druginTotal);
}
