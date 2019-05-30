package com.wilson.cms.vo;

import java.io.Serializable;

public class ExistsArgs implements Serializable {

   private  ExistsType Type;
   private  String Keyword;
   public  Long ID;

    public ExistsType getType() {
        return Type;
    }

    public void setType(ExistsType type) {
        Type = type;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}