package com.wilson.cms.vo;

import com.wilson.cms.po.MaterialPo;

/**
 * @ClassName MateridalVo
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/22 13:32
 * @Version 1.0
 **/
public class MateridalVo {

    private MaterialPo materialPo;

    private  String urls;

    public MaterialPo getMaterialPo() {
        return materialPo;
    }

    public void setMaterialPo(MaterialPo materialPo) {
        this.materialPo = materialPo;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
