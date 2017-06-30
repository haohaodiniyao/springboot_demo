package com.example.kaptcha_demo;

import com.google.common.io.Files;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by yaokai on 2017/6/30.
 */
@RestController
public class FileUploadController {
    private int maxPostSize = 35000 * 1024 * 1024;
    @RequestMapping("/upload")
    public void upload(HttpServletRequest req, HttpServletResponse res) throws Exception{
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setSizeThreshold(4096);
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        upload.setSizeMax(maxPostSize);
//        upload.setHeaderEncoding("UTF-8");
         Collection<Part> list = req.getParts();
        for(Part part:list){
            Collection<String> headerNames = part.getHeaderNames();
            for(String headerName:headerNames){
                System.out.println("headerName:"+headerName+"#"+part.getHeader(headerName));
            }
            String fileName = part.getSubmittedFileName();
            System.out.println("fileName:"+fileName+"#contentType:"+part.getContentType()+"#size:"+part.getSize());
            InputStream is = part.getInputStream();
            String fileNameNew = UUID.randomUUID().toString();
            Files.write(org.apache.commons.io.IOUtils.toByteArray(is),new File(fileNameNew+"."+Files.getFileExtension(fileName)));
        }
    }
}
