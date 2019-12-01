package com.wm.po;

/**
 * 爬虫结果返回
 * 
 * @author CHENT
 *
 */
public class SpiderFruit {
	// 服务器响应的状态码
	private Integer StatusCode=null;

	// 访问的页面类型
	private String ContentType=null;

	// 页面内容:html等
	private String entitystr=null;

	public Integer getStatusCode() {
		return StatusCode;
	}

	public String getContentType() {
		return ContentType;
	}



	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	public String getEntitystr() {
		return entitystr;
	}



	public void setStatusCode(Integer statusCode) {
		StatusCode = statusCode;
	}

	public void setEntitystr(String entitystr) {
		this.entitystr = entitystr;
	}

	@Override
	public final String toString() {
		return "SpiderFruit [StatusCode=" + StatusCode + ", ContentType=" + ContentType + ", entitystr=" + entitystr
				+ "]";
	}
		

}
