package com.rbhp.geohandbook.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "pub_date")
    public String pubDate;
    @ColumnInfo(name = "link")
    public String link;
    @ColumnInfo(name = "guid")
    public String guid;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "enclosure_link")
    public String enclosureLink;
    @ColumnInfo(name = "enclosure_type")
    public String enclosureType;

    public NewsEntity(String title, String pubDate, String link, String guid, String author, String description, String content, String enclosureLink) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.guid = guid;
        this.author = author;
        this.description = description;
        this.content = content;
        this.enclosureLink = enclosureLink;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", enclosureLink='" + enclosureLink + '\'' +
                '}';
    }
}
