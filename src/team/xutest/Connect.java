package team.xutest;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Connect {
    // 与 x-utest 建立连接

    String base_url;
    String app_id;
    String app_key;
    String pro_id;
    String token;

    // 构造函数, 属性值
    public Connect(String base_url,String app_id,String app_key,String pro_id) throws IOException {
        this.base_url = base_url;
        this.app_id = app_id;
        this.app_key = app_key;
        this.pro_id = pro_id;
    }

    // x-utest 认证, 获取 token
    public void auth() throws Exception {
        String url = base_url + "/testdata/api-auth/";
        URL obj = new URL(url);
        HttpURLConnection con = null;
        try{
        	con = (HttpURLConnection) obj.openConnection();
        }catch(Exception e){
        	System.out.println("连接失败！obj为："+obj+"   异常信息为："+e);
        }

        //添加请求内容
        con.setRequestMethod("POST");
        String urlParameters = "appid_form=" + app_id + "&appkey_form=" + app_key;

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        String jsonStr = response.toString();

        //转换成JSON数据并查找token
        JSONObject jarr = JSON.parseObject(jsonStr);//JSON.parseArray(jsonStr);
        token = jarr.getJSONObject("data").getString("token");
    }

    // 发送测试结果到 x-utest
    public String post_test_result(JSONObject test_data) throws Exception {
        String url = base_url + "/testdata/create-test-data/?token=" + token;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //添加请求内容
        con.setRequestMethod("POST");
//        String urlParameters = test_data;

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(test_data.toJSONString());
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //返回结果
        String jsonStr = response.toString();
        return jsonStr;
    }
}
