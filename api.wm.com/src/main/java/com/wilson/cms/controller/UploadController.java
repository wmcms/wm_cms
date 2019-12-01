package com.wilson.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wilson.cms.config.Cms;
import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.utils.FileUtils;
import com.wilson.cms.vo.FileVo;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.UploadMethod;

/**
 * @ClassName UploadController
 * @Description 文件上传控制器
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

@RestController
public class UploadController {

private  MultipartFile[] files ;
private  UploadMethod method;
    @Autowired
    Cms cms;
    /**
     * 统一上传入口
     * @param type
     * @param files
     * @return
     */
    @PostMapping("/upload/{method}")
    public Result Index(@PathVariable UploadMethod method,  MultipartFile[] files) throws Exception {
        FileUtils.Config(cms);
        this.method =method;
        this.files =files;
        switch (method){
            case file:
               return this.UploadFile();
            case head:
                return  this.UploadHead();
            case image:
                return  this.UploadImage();
            case video:
                return this.UploadVideo();
                default:
                    return Result.Error("不支持");
        }
    }

    Result UploadImage() throws Exception {
        List<FileVo> items = new ArrayList<>();
        if(this.files != null && this.files.length >0){
            for (MultipartFile file :this.files) {
                FileVo item = new FileVo();
                String fileName = file.getOriginalFilename();
                item.setName(fileName);
                item.setSize(file.getSize());
                item.setType(file.getContentType());
                item.setUrl(FileUtils.saveFile(method, file.getBytes(), fileName.substring(fileName.lastIndexOf("."))));
                items.add(item);
            }
        }
        return Result.Success(items);
    }
    Result UploadFile(){
        return Result.Success(null);
    }
    Result UploadHead(){
        return Result.Success(null);
    }
    Result UploadVideo(){
        return Result.Success(null);
    }


    @PostMapping("/postfile/{imgType}")
    public  Object PostFile(@PathVariable UploadMethod method,String name, MultipartFile[] files){
            if(files != null && files.length >0){
                for (MultipartFile file :files){
                    MaterialPo item = new MaterialPo();
                    item.setName(name);

                    System.out.println("contentType="+file.getContentType());
                    System.out.println("contentType="+file.getOriginalFilename());
                    System.out.println("contentType="+file.getName());
                    System.out.println("contentType="+file.getSize());
                    System.out.println("contentType="+file.getResource());


//                    String contentType = file.getContentType();
//                    String fileName = file.getOriginalFilename();
//                    System.out.println("fileName-->" + fileName);
//                    System.out.println("getContentType-->" + contentType);
//                    String resfileNewName = FileUtils.saveFile( method,file.getBytes());
                }


//                    proPic.setPicUrl(filePath +resfileNewName);
//                    if(imgType.equals("list")){
//                        proPic.setPicName("列表图");
//                    }else if("caro".equals(imgType)){
//                        proPic.setPicName("轮播图");
//                    }else if("intr".equals(imgType)){
//                        proPic.setPicName("介绍图");
//                    }else if("thum".equals(imgType)){
//                        proPic.setPicName("缩略图");
//                    }
//                    proPicList.add(proPic);

            }
            return Result.Success(null);


    }
}
