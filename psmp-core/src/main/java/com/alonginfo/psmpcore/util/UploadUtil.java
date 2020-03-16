package com.alonginfo.psmpcore.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class UploadUtil {
    public static Map<String,String> upload(MultipartFile pic,String uploadUrl) {
        Integer nowYear = DateUtils.getNowYear();//获取当前的年时间
        int nowMonth = DateUtils.getNowMonth();//获取当前月
        String url=uploadUrl+"/"+nowYear+"/"+nowMonth;
        File fileDirectory=new File(url);
        if(!fileDirectory.exists()){//文件夹是否存在，不存在创建
            fileDirectory.mkdirs();
        }
        String filename = pic.getOriginalFilename(); //文件名
        //文件截取格式
        String fileTpye = filename.substring(filename.lastIndexOf(".") + 1).trim().toLowerCase();
        Map<String,String> map=new HashMap<String,String>();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = pic.getInputStream();
            // 给新文件拼上时间毫秒，
            long now = System.currentTimeMillis();
            File file = new File(url, "p" + now +"."+ fileTpye);
            outputStream = new FileOutputStream(file);
            byte temp[] = new byte[1024];
            int size = -1;
            while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
                outputStream.write(temp, 0, size);
            }
            outputStream.flush();
            map.put("fileName",url+"/"+file.getName());
            map.put("fileSize",file.length()+"");
            map.put("fileType",fileTpye);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null)
                    inputStream.close();
                if(outputStream!=null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
