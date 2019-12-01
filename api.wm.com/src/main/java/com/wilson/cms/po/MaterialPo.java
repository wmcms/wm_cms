package com.wilson.cms.po;

public class MaterialPo extends PoBase {

    private  Long metaId;
    private  String name;
    private  String fileName;
    private  String fileType;
    private   Long fileSize;
    private String url;
	public Long getMetaId() {
		return metaId;
	}
	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
}