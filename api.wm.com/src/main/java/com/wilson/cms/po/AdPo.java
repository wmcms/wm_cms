package com.wilson.cms.po;

public class AdPo extends TEntity {
    /**
     * 分类ID
     */
    private  Long metaId;
    /**
     * 父级ID
     */
    private  Long parentId;
    /**
     * 名称
     */
    private  String name;
    /**
     * 父级路径
     */
    private  String parentPath;
    /**
     * 层级
     */
    private  Short level;

    /**
     * 类型
     */
    private  Short type;
    /**
     * 值越小越靠前，值相同按修改时间倒序排列
     */
    private  Short sort;

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }
    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}