package com.example.demo.common.utils;

import com.example.demo.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhupeng01
 */
@Slf4j
public class HttpUtil {
    /**
     * 处理http请求
     * @param url        发送请求的URL
     * @return
     */
    public static String send(String url,String method,String param) throws BusinessException {
        StringBuilder result = new StringBuilder();
        OutputStreamWriter writer = null;
        BufferedReader in = null;
        int responseCode = 0;
        try {
            URL urlObject = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) urlObject.openConnection();
            // 字符集utf-8
            uc.setRequestProperty("Charset", "utf-8");
            uc.setConnectTimeout(3000);//设置连接主机超时（单位：毫秒）
            uc.setReadTimeout(3000);//设置从主机读取数据超时（单位：毫秒）
            uc.setRequestMethod(method);
            if (StringUtils.isNotBlank(param)) {
                //设置数据格式
                uc.setRequestProperty("Content-Type", "application/json");
                // 是否允许输入输出
                uc.setDoInput(true);
                uc.setDoOutput(true);
                writer = new OutputStreamWriter(uc.getOutputStream());
                // 发送参数 utf-8格式
                writer.write(new String(param.getBytes(), "utf-8"));
                // 清理当前编辑器的左右缓冲区，并使缓冲区数据写入基础流
                writer.flush();
            }
            responseCode = uc.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK
                    || responseCode == HttpURLConnection.HTTP_NO_CONTENT
                    || responseCode == HttpURLConnection.HTTP_CREATED) {
                in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }
            } else {
                throw new BusinessException("返回数据异常");
            }
        } catch (Exception e) {
            //throw new BusinessException("连接超时");
            log.error(url+" 连接超时");
        } finally {
            try {
                if (in != null) {
                    if (writer != null) {
                        writer.close();
                    }
                    in.close();
                }
            } catch (Exception e) {
                throw new BusinessException("关闭连接异常");
            }
        }
        return result.toString();
    }

}