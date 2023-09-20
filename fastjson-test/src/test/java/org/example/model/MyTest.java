package org.example.model;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class MyTest {
    @Test
    public void testToJson() {
        Student student = new Student();
        student.setId(2001);
        student.setName("张三");
        student.setAge(22);

        // 把对象转 json
        String json = JSONObject.toJSONString(student);

        System.out.println(json);
    }

    @Test
    public void testToObject() {
        String json = "{\"age\":22,\"id\":2001,\"name\":\"张三\"}";

        // json 转对象
        Student student = JSONObject.parseObject(json,Student.class);

        System.out.println(student);
    }

    // 获取 name key 的值
    @Test
    public void testAccessValue() {
        String json = "{\"age\":22,\"id\":2001,\"name\":\"张三\"}";

        // 这是一个 JSONObject 对象，它是一个 map
        JSONObject jsonObject = JSONObject.parseObject(json);

        String name = jsonObject.getString("name");

        System.out.println(name);
    }
}
