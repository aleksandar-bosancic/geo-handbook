package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

public class NewsData {
    @SerializedName("title")
    private String title;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("link")
    private String link;
    @SerializedName("guid")
    private String guid;
    @SerializedName("author")
    private String author;
    @SerializedName("description")
    private String description;
    @SerializedName("content")
    private String content;
    @SerializedName("enclosure")
    private Enclosure enclosure;

    public NewsData(String title, String pubDate, String link, String guid, String author, String description, String content) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.guid = guid;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public class Enclosure {
        @SerializedName("link")
        private String link;
        @SerializedName("type")
        private String type;

        public Enclosure(String link, String type) {
            this.link = link;
            this.type = type;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
