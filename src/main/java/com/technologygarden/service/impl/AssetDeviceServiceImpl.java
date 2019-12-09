package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.dao.DevicePropertyMapper;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.DeviceProperty;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.AssetDeviceService;
import com.technologygarden.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assetDeviceService")
public class AssetDeviceServiceImpl implements AssetDeviceService {

    private final DeviceMapper deviceMapper;
    private final DevicePropertyMapper devicePropertyMapper;

    @Autowired
    public AssetDeviceServiceImpl(DeviceMapper deviceMapper, DevicePropertyMapper devicePropertyMapper) {
        this.deviceMapper = deviceMapper;
        this.devicePropertyMapper = devicePropertyMapper;
    }

    @Override
    public ResultBean<Page<Device>> getDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Device> deviceList = deviceMapper.selectDeviceListWithPropertyByPage();
        return new ResultBean<>(deviceList);

    }

    @Override
    @Transactional
    public ResultBean<?> insertDeviceWithPropertyDynamic(Device device) {

        // 判断参数是否缺失
        if(device.getCategoryId() == null || StringUtil.empty(device.getDeviceName()) || device.getOwner() == null){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }

        List<PropertyDevice> propertyDeviceList = device.getPropertyDeviceList();

        for (PropertyDevice propertyDevice : propertyDeviceList) {

            DeviceProperty deviceProperty = propertyDevice.getDeviceProperty();
            if (deviceProperty.getCategoryId() == null || deviceProperty.getDeviceId() == null || deviceProperty.getPropertyId() == null || StringUtil.empty(deviceProperty.getPropertyValue())) {
                return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
            }

        }

        device.setKind(1);
        device.setRemain(device.getTotal());
        // 先插入设备信息
        deviceMapper.insert(device);
        // 再插入设备相关属性
        devicePropertyMapper.insertForeach(propertyDeviceList);


        return new ResultBean<>();

    }

    @Override
    @Transactional
    public ResultBean<?> deleteDeviceById(Integer deviceId) {

        // 判断设备是否被使用
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if(!device.getTotal().equals(device.getRemain())){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }

        devicePropertyMapper.deleteByDeviceByDeviceId(deviceId);
        deviceMapper.deleteByPrimaryKey(deviceId);
        return new ResultBean<>();
    }

    @Override
    @Transactional
    public ResultBean<?> updateDeviceById(Device device) {

        // 判断id是否缺失
        if (device.getDeviceId() == null){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }

        List<PropertyDevice> propertyDeviceList = device.getPropertyDeviceList();

        for (PropertyDevice propertyDevice : propertyDeviceList) {

            DeviceProperty deviceProperty = propertyDevice.getDeviceProperty();
            if (deviceProperty.getDevicePropertyId() == null) {
                return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
            }

        }

        deviceMapper.updateByIdDynamic(device);
        devicePropertyMapper.updateByDeviceIdDynamic(propertyDeviceList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<Device>> searchDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String deviceName, Integer owner) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Device> deviceList = deviceMapper.searchDeviceListWithPropertyByPage(categoryId, deviceName, owner);
        return new ResultBean<>(deviceList);

    }
}
