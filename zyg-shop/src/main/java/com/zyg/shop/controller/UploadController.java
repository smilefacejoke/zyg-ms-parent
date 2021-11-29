package com.zyg.shop.controller;

import com.zyg.common.utils.FastDFSClient;
import com.zyg.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 涛哥
 * @date 2021/11/29
 */

@RestController
public class UploadController {

    @PostMapping("/shop/upload")
    //必须与前端传入的formData中的key名称保持一致
    public R upload(MultipartFile file) throws Exception {
        //1.1 构造fastDFS客户端（工具对象）
        FastDFSClient client=new FastDFSClient("classpath:fastdfs.conf");
        //1.2 得到文件名
        String filename=file.getOriginalFilename();
        //1.3 得到文件后缀名
        String extName=filename.substring(filename.lastIndexOf(".")+1);
        //1.4 开始文件上传
        //参数1：上传的二进制内容，参数2：文件名称
        String path = client.uploadFile(file.getBytes(), extName);
        //1.5 得到上传的文件路径
        String url="http://192.168.56.12:8080/" + path;
        return R.ok().put("url",url);
    }

}
