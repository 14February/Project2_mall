package com.cskaoyan.map;

import com.cskaoyan.bean.backstage.Log;
import com.cskaoyan.mapper.backstage.LogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
@Component
public class OperationMap{

    public static Map<Integer,String> operations = new HashMap<>();

    @Autowired
    LogMapper logMapper;

    static{
        Properties properties = new Properties();
        String property = System.getProperty("user.dir");
        String path = property + "\\src\\main\\resources\\operations.properties";
        File file = new File(path);
        System.out.println(file.exists());
        try
        {
            properties.load(new FileReader(path));
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        for(int i = 0;i <= 32;i++){
            operations.put(i,properties.getProperty("" + i));
        }
    }

//    static {
//        Properties properties = null;
//        try {
//            Reader reader = Resources.getResourceAsReader("operations.properties");
//            properties = new Properties();
//            properties.load(reader);
//            int size = properties.size();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 17; i++) {
//            operations.put(i, (String)properties.get(i));
//        }
//        System.out.println(1);
//    }

    public void insertOperations(HttpServletRequest request,Subject subject,String action,Boolean status){
        String username = subject.getPrincipals().toString();
        Log log = new Log(null,username,request.getRemoteAddr(),1,action,status,null,null,new Date(),new Date(),false);
        logMapper.insertSelective(log);
    }
}
