package org.example;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    // 可封装作为工具类
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

    @Test
    public void testPost() {
        String url = "https://restapi.amap.com/v3/ip";

        // 1、创建 HttpClient 对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 2、创建 HttpPost 对象
        HttpPost httpPost = new HttpPost(url);

        // 3、准备 post 请求的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        // 添加参数 BasicNameValuePair 类实现 NameValuePair 接口
        // BasicNameValuePair(key,value)
        params.add(new BasicNameValuePair("key","0113a13c88697dcea6a445584d535837"));
        params.add(new BasicNameValuePair("ip","60.25.188.64"));

        // 4、设置 HttpPost 使用参数
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            // 5、执行 post 请求
            CloseableHttpResponse response = client.execute(httpPost);

            // 6、读取数据
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
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
