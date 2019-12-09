package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDevice {

    private Integer propertyDeviceId;
    private Integer kind;
    private Integer categoryId;
    private String categoryName;
    private String property;
    private DeviceProperty deviceProperty = null;

}