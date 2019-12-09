package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PlatformApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface PlatformApplicationMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(PlatformApplication record);

    PlatformApplication selectByPrimaryKey(Integer pId);

    Page<PlatformApplication> selectAll();

    int updateByPrimaryKey(PlatformApplication record);
}