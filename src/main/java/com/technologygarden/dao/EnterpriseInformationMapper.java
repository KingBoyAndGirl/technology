package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.LegalPerson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface EnterpriseInformationMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(EnterpriseInformation record);

    EnterpriseInformation selectByPrimaryKey(Integer cId);

    List<EnterpriseInformation> selectAll();

    Page<EnterpriseInformation> selectAllByPageWithoutCommittee();

    int updateByPrimaryKey(EnterpriseInformation record);

    int insertReturnPrimaryKey(EnterpriseInformation enterpriseInformation);

    List<EnterpriseInformation> selectAllWithoutCommittee();
}