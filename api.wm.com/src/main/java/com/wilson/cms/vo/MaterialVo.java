package com.wilson.cms.vo;

import java.util.List;

import com.wilson.cms.po.MaterialPo;

/**
 * @ClassName MateridalVo
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/22 13:32
 * @Version 1.0
 **/
public class MaterialVo {

    private MaterialPo materialPo;

    private List<FileVo> files;

    public MaterialPo getMaterialPo() {
        return materialPo;
    }

    public void setMaterialPo(MaterialPo materialPo) {
        this.materialPo = materialPo;
    }

    public List<FileVo> getFiles() {
        return files;
    }

    public void setFiles(List<FileVo> files) {
        this.files = files;
    }
}
