package com.hzq.controller;

import com.hzq.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {


    String folder="E:\\work\\authprictive\\hzq-security-demo\\src\\main\\java\\com\\hzq\\controller";

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping
    public FileInfo upload(@RequestBody MultipartFile file) throws IOException {
        System.out.println( file.getName());
        /**
         * //文件原始名字
         */
        System.out.println( file.getOriginalFilename());
        System.out.println( file.getSize());

        File localFile=new File(folder,System.currentTimeMillis()+".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 文件下载
     */

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try (
                InputStream inputStream=new FileInputStream(new File(folder,id+".txt"));
                OutputStream outputStream=response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("Content-Dispostion","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }catch (Exception e){

        }
    }
}
