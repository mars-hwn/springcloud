package com.alonginfo.psmpcore.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


public class DownloadUtil {
    /**
     * 下载
     * @param fileid
     * @param request
     * @param response
     */
    public static void download(String fileid, HttpServletRequest request, HttpServletResponse response) {
        //设置文件路径
        File file = new File(fileid);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setHeader("content-type", "application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileid,"UTF-8"));// 设置文件名
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bis!=null)
                        bis.close();
                    if(fis!=null)
                        fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
