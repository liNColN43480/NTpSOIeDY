// 代码生成时间: 2025-09-20 21:22:49
package com.example.micronaut.model;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

// 数据模型设计
@Introspected
public class DataModel {

    // UUID作为唯一标识符
    private UUID id;

    // 非空的名称字段
    @NotBlank(message = "Name cannot be empty")
    private String name;

    // 非空的描述字段
    @NotBlank(message = "Description cannot be empty")
    private String description;

    // 构造函数
    public DataModel() {
    }

    // 带参数的构造函数
    public DataModel(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = UUID.randomUUID(); // 生成唯一的ID
    }

    // ID的getter和setter方法
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // 名称的getter和setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 描述的getter和setter方法
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // 重写toString方法，方便调试
    @Override
    public String toString() {
        return "DataModel{"id":"" + id + "", "name":"" + name +
                "", "description":"" + description + ""}";
    }
}
