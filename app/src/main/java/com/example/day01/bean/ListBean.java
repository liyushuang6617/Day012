package com.example.day01.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ListBean {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String title;

    @Property
    private String chapterName;

    @Property
    private String envelopePic;

    @Generated(hash = 1225256111)
    public ListBean(Long id, String title, String chapterName, String envelopePic) {
        this.id = id;
        this.title = title;
        this.chapterName = chapterName;
        this.envelopePic = envelopePic;
    }

    @Generated(hash = 777734033)
    public ListBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getEnvelopePic() {
        return this.envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }
}
