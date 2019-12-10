package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.MaintainMapper;
import com.technologygarden.dao.ServiceApplicationMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.ServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceApplicationService")
public class ServiceApplicationServiceImpl implements ServiceApplicationService {
    private final ServiceApplicationMapper serviceApplicationMapper;
    private final MaintainMapper maintainMapper;
    @Autowired
    public ServiceApplicationServiceImpl(ServiceApplicationMapper serviceApplicationMapper, MaintainMapper maintainMapper) {
        this.serviceApplicationMapper = serviceApplicationMapper;
        this.maintainMapper = maintainMapper;
    }

    @Override
    public ResultBean<Page<ServiceApplication>> getServiceApplicationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ServiceApplication> serviceApplications=serviceApplicationMapper.getServiceApplicationByPage(cId);
        for(ServiceApplication serviceApplication:serviceApplications){
            serviceApplication.setServicename(maintainMapper.selectByPrimaryKey(serviceApplication.getMaintainId()).getServicename());
        }
        return new ResultBean<>(serviceApplications);
    }

    @Override
    public ResultBean insertServiceApplication(ServiceApplication serviceApplication) {
        return new ResultBean(serviceApplicationMapper.insert(serviceApplication)) ;
    }

    @Override
    public ResultBean updateServiceApplication(ServiceApplication serviceApplication) {
        return new ResultBean(serviceApplicationMapper.updateByPrimaryKey(serviceApplication));
    }

    @Override
    public ResultBean deleteServiceApplication(Integer id) {
        return new ResultBean(serviceApplicationMapper.deleteByPrimaryKey(id));
    }
}