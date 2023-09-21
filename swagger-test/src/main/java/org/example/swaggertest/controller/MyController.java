package org.example.swaggertest.controller;

import io.swagger.annotations.*;
import org.example.swaggertest.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 接口文档定制
@Api(tags = "用户功能接口")
@RestController
public class MyController {

    /**
     * ApiOperation: 说明方法的作用
     * 使用位置：控制器方法的上面
     */
    @ApiOperation(value = "swagger 测试使用",notes = "第一个 swagger 控制器方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户姓名",required = true,defaultValue = "Mrying",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id",value = "用户主键",required = true,defaultValue = "2000300211",dataType = "Long",paramType = "query")
    })
    @GetMapping("/hello")
    public String helloSwagger(String name,Integer id) {
        return "helloSwagger";
    }

    @ApiOperation(value = "查询学生",notes = "根据主键 id 查询学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",required = true,dataType = "Long")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "接口访问成功",response = Student.class),
            @ApiResponse(code = 404,message = "接口访问失败")
    })
    @GetMapping("/query/id")
    public Student queryStudent(Integer id) {
        Student student = new Student();
        student.setId(2000300211);
        student.setName("Mrying");

        return student;
    }
}
