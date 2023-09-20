package org.example;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {
    @Test
    public void testGet() throws IOException {
        // 发起 get请求，编程方式处理 http 请求
        String url = "https://restapi.amap.com/v3/ip?key=0113a13c88697dcea6a445584d535837&ip=60.25.188.64";

        // 1、创建 HttpClient 对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 2、创建 HttpGet 对象
        HttpGet httpGet = new HttpGet(url);

        // 3、执行请求，使用 client 对象的方法，执行请求后获取返回结果
        // 相当于 HttpServletResponse，是返回结果
        try {
            CloseableHttpResponse response = client.execute(httpGet);

            // 从 response 获取应答信息
            // HttpStatus.SC_OK == 200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获取数据
                // 实体类
                String json = EntityUtils.toString(response.getEntity());
                System.out.println(json);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
