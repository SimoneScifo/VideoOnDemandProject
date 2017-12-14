package com.videoondemand.model;

import java.util.Date;

public class Genre {


    private int id;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifyDate;

    public Genre (String name,String description, Date creationDate, Date lastModifyDate){
        this.name=name;
        this.description=description;
        this.creationDate=creationDate;
        this.lastModifyDate=lastModifyDate;
    }

    public void setId (int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

}
