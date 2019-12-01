package com.wilson.cms.vo;

import java.io.Serializable;
import java.util.List;

public class ImageCodeVo implements Serializable {

	private String id;
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}