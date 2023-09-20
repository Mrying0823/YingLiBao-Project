package org.example.model;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class ComplexJsonTest {
    @Test
    public void test1() {
        Student student = new Student();
        student.setId(2001);
        student.setAge(22);
        student.setName("张三");

        School school = new School();
        school.setName("桂林电子科技大学");
        school.setAddress("广西壮族自治区桂林市灵川县灵田镇桂林电子科技大学");
        student.setSchool(school);

        // JSON 字符串中的属性的顺序不一定会遵循在 toString() 方法中定义的顺序。
        // JSON 对象是无序的，属性的顺序是不确定的。
        String json = JSONObject.toJSONString(student);

        System.out.println(json);
    }

    // 反序列化
    @Test
    public void test2() {
        String json = "{\"age\":22,\"id\":2001,\"name\":\"张三\",\"school\":{\"address\":\"广西壮族自治区桂林市灵川县灵田镇桂林电子科技大学\",\"name\":\"桂林电子科技大学\"}}\n";

        Student student = JSONObject.parseObject(json,Student.class);

        System.out.println(student);
    }

    @Test
    public void testRead() {
        String json = "{\"age\":22,\"id\":2001,\"name\":\"张三\",\"school\":{\"address\":\"广西壮族自治区桂林市灵川县灵田镇桂林电子科技大学\",\"name\":\"桂林电子科技大学\"}}\n";

        String string = JSONObject.parseObject(json).getJSONObject("school").getString("address");

        System.out.println(string);
    }
}
