package com.wilson.cms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wilson.cms.config.Cms;
import com.wilson.cms.vo.UploadMethod;

/**
 * @ClassName FileUtils
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 0:29
 * @Version 1.0
 **/
public class FileUtils {

    static  Cms CFG;

  public static void  Config(Cms cms){
      CFG=cms;
  }


    public static String saveFile(UploadMethod method, byte[] file,String extendName) throws Exception{
        Long dirId=StringUtils.newLongId(FileUtils.class);
        String urlPath=String.format("/%s/%s",method,new SimpleDateFormat(Constant.DATE_DIR_FORMAT).format(new Date()));
        String saveDir =String.format("%s%s/",CFG.getUploadDir(),urlPath );
        createDir(saveDir);
        String fileName=String.format("%s%s",dirId,extendName);
        FileOutputStream out = new FileOutputStream(String.format("%s%s",saveDir,fileName));
        out.write(file);
        out.flush();
        out.close();

        return String.format("%s%s/%s",CFG.getMatUrl(),urlPath,fileName);
    }

    public  static  void  RemoveAt(String url) {
        url = url.toLowerCase();
        String path = url.replaceFirst(CFG.getMatUrl().toLowerCase(), CFG.getUploadDir());
        File file = new File(path);
        file.deleteOnExit();
    }

     static  void createDir(String dir){
         File dirInfo = new File(dir);
         if(!dirInfo.exists()){
             dirInfo.mkdirs();
         }
     }

}
