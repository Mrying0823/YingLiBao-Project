package org.example.swaggertest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @since 2023/9/22 0:37
 *
 * ApiModel
 * 用在类上，表示对类进行说明，用于实体类中的参数接收说明
 *
 * ApiModelProperty
 * 用于字段，表示对 model 属性的说明
 */
@ApiModel(description = "数据类，表示学生信息")
public class Student {
    @ApiModelProperty(value = "学生主键")
    private Integer id;
    @ApiModelProperty(value = "学生姓名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
