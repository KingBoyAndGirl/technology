package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/asset")
@Api(tags = "资产管理 / 房产接口", value = "AssetBuildingController")
public class AssetBuildingController {

    private final AssetBuildingService assetBuildingService;

    @Autowired
    public AssetBuildingController(AssetBuildingService assetBuildingService) {
        this.assetBuildingService = assetBuildingService;
    }

    @RequestMapping(value = "/building", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取房产列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<Page<Building>> getBuildingByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetBuildingService.getBuildingByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/building", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除房产", notes = "参数包括：房产主键id")
    public ResultBean deleteBuildingById(@NonNull Integer buildingId){

        return assetBuildingService.deleteBuildingById(buildingId);

    }

    @RequestMapping(value = "/building", method = RequestMethod.POST)
    @ApiOperation(value = "新增房产", notes = "参数包括：房产对象list，一个房产也需放入list中")
    public ResultBean insertBuildingForeach(@NonNull @RequestBody List<Building> buildingList){

        return assetBuildingService.insertBuildingForeach(buildingList);

    }

    @RequestMapping(value = "/building", method = RequestMethod.PUT)
    @ApiOperation(value = "修改房产信息", notes = "参数包括：房产对象")
    public ResultBean updateBuildingById(@RequestBody Building building){

        return assetBuildingService.updateBuildingById(building);

    }

    @RequestMapping(value = "/building/search", method = RequestMethod.GET)
    @ApiOperation(value = "根据名称搜索房产", notes = "参数包括：页数，每页数量，房产名称")
    public ResultBean<Page<Building>> searchBuildingByName(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String buildingName){

        return assetBuildingService.searchBuildingByName(pageNum, pageSize, buildingName);

    }

}
