package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.CompanyRoomDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyRoomDeviceMapper {
    int deleteByPrimaryKey(Integer crdId);

    int insert(CompanyRoomDevice record);

    CompanyRoomDevice selectByPrimaryKey(Integer crdId);

    List<CompanyRoomDevice> selectAll();

    int updateByPrimaryKey(CompanyRoomDevice record);

    CompanyRoomDevice selectByCompanyDeviceRoom(@Param("companyId") Integer companyId, @Param("deviceId") Integer deviceId, @Param("roomId") Integer roomId);

    int updateDynamic(CompanyRoomDevice companyRoomDevice);

    int updateNumber(@Param("crdId") Integer crdId, @Param("number") Integer number);

    Page<CompanyRoomDevice> selectWithInfoByPage();
}