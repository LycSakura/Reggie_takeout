package com.example.reggie.controller;

import com.example.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/***
 *@title CommonController
 *@CreateTime 2024/1/29 17:35
 *@description 文件上传和下载
 **/
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;

    /**
     * @param file
     * @return R<String>
     * @description: 文件的上传
     */
    @PostMapping("upload")
    public R<String> upload(MultipartFile file) {
        //file是临时文件，需要转存
        log.info(file.toString());
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //截取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，创建目录
            dir.mkdirs();
        }
        try {
            //将临时文件转存到指定位置
//            file.transferTo(new File(basePath + fileName));
            file.transferTo(new File(basePath + fileName));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }

    /**
     * @param name
     * @param response
     * @description: 文件的下载
     */
    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response) {
        try {
            //输入流，读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流，将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //响应回去上面类型的文件
            response.setContentType("image/jpeg");

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
