package com.wilson.cms.controller;

import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.UploadMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UploadController
 * @Description 文件上传控制器
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

@RestController
public class UploadController {

    private UploadMethod type;
    private MultipartFile[] files;

    /**
     * 统一上传入口
     * @param type
     * @param files
     * @return
     */
    @PostMapping("/upload/{type}")
    public Result Index(@PathVariable("type") UploadMethod type, @RequestParam("file") MultipartFile[] files){
        this.type = type;
        this.files = files;
        switch (type){
            case FILE:
               return this.UploadFile();
            case HEAD:
                return  this.UploadHead();
            case IMAGE:
                return  this.UploadImage();
            case VIDEDO:
                return this.UploadVideo();
                default:
                    return Result.Error("不支持");
        }
    }

    Result UploadImage(){
        return Result.Success(null);
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
}
