package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(Room record);

    Room selectByPrimaryKey(Integer rId);

    List<Room> selectAll();

    int updateByPrimaryKey(Room record);

    Page<Room> selectRoomGardenWithBuildingByPage();

    Page<Room> selectRoomCompanyWithBuildingByPage();

    int updateRoomDynamic(Room roomGarden);

    int insertRoomGardenForeach(List<Room> roomGardenList);

    int insertRoomCompanyForeach(List<Room> roomCompanyList);

    Page<Room> searchRoomGardenByPage(@Param("buildingId") Integer buildingId, @Param("status") Integer status, @Param("roomName") String roomName);

    Page<Room> searchRoomCompanyByPage(@Param("buildingId") Integer buildingId, @Param("status") Integer status, @Param("roomName") String roomName);

}