package sya.autoLogin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.protocol.HttpClientContext;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  
import org.apache.http.NameValuePair;  
  
public class HttpUtils {  
      
    private static CloseableHttpClient httpClient = HttpClients.createDefault();    
    private static HttpClientContext context = new HttpClientContext();    
      
    private HttpUtils() {    
            
    }   
      
    public static String sendGet(String url) {    
        CloseableHttpResponse response = null;    
        String content = null;    
        try {    
            HttpGet get = new HttpGet(url);    
            response = httpClient.execute(get, context);    
            HttpEntity entity = response.getEntity();    
            content = EntityUtils.toString(entity);    
            EntityUtils.consume(entity);    
            return content;    
        } catch (Exception e) {    
            e.printStackTrace();    
            if (response != null) {    
                try {    
                    response.close();    
                } catch (IOException e1) {    
                    e1.printStackTrace();    
                }    
            }    
        }    
        return content;    
    }    
    public static String sendGet(String url,Map<String,String> headers) {    
        CloseableHttpResponse response = null;    
        String content = null;    
        try {    
        	HttpGet get = new HttpGet(url); 
        	if(headers!=null&&headers.size()>0){
        		Iterator<Entry<String,String>> it=headers.entrySet().iterator();
        		while(it.hasNext()){
        			Entry<String,String> entry=it.next();
        			get.setHeader(entry.getKey(), entry.getValue());
        		}
        	}
            response = httpClient.execute(get, context);    
            HttpEntity entity = response.getEntity();    
            content = EntityUtils.toString(entity);    
            EntityUtils.consume(entity);    
            return content;    
        } catch (Exception e) {    
            e.printStackTrace();    
            if (response != null) {    
                try {    
                    response.close();    
                } catch (IOException e1) {    
                    e1.printStackTrace();    
                }    
            }    
        }    
        return content;    
    } 
    public static String sendPost(String url, List<NameValuePair> nvps) {    
        CloseableHttpResponse response = null;    
        String content = null;    
        try {    
            //　HttpClient中的post请求包装类    
            HttpPost post = new HttpPost(url);    
            // nvps是包装请求参数的list    
            if (nvps != null) {    
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));    
            }    
            // 执行请求用execute方法，content用来帮我们附带上额外信息    
            response = httpClient.execute(post, context);    
            // 得到相应实体、包括响应头以及相应内容    
            HttpEntity entity = response.getEntity();    
            // 得到response的内容    
            content = EntityUtils.toString(entity);    
            //输出html  
            System.out.println(content);  
            //　关闭输入流    
            EntityUtils.consume(entity);    
            return content;    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            if (response != null) {    
                try {    
                    response.close();    
                } catch (IOException e) {    
                    e.printStackTrace();    
                }    
            }    
        }    
        return content;    
    }    
    public static String sendPost(String url, List<NameValuePair> nvps,Map headers) {    
        CloseableHttpResponse response = null;    
        String content = null;    
        try {    
            //　HttpClient中的post请求包装类    
            HttpPost post = new HttpPost(url);    
            // nvps是包装请求参数的list    
            if (nvps != null) {    
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));    
            }    
            if(headers!=null&&headers.size()>0){
        		Iterator<Entry<String,String>> it=headers.entrySet().iterator();
        		while(it.hasNext()){
        			Entry<String,String> entry=it.next();
        			post.setHeader(entry.getKey(), entry.getValue());
        		}
        	}
            // 执行请求用execute方法，content用来帮我们附带上额外信息    
            response = httpClient.execute(post, context);    
            // 得到相应实体、包括响应头以及相应内容    
            HttpEntity entity = response.getEntity();    
            // 得到response的内容    
            content = EntityUtils.toString(entity);    
            //输出html  
            System.out.println(content);  
            //　关闭输入流    
            EntityUtils.consume(entity);    
            return content;    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            if (response != null) {    
                try {    
                    response.close();    
                } catch (IOException e) {    
                    e.printStackTrace();    
                }    
            }    
        }    
        return content;    
    } 
}  
