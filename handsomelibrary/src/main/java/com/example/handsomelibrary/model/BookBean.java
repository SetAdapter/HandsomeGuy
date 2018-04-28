package com.example.handsomelibrary.model;

import java.util.List;

/**
 * Created by Stefan on 2018/4/27.
 */

public class BookBean {

    /**
     * _id : 5a377a0ba297617a31d39cdc
     * title : 施法诸天.
     * author : 海拉斯特黑袍
     * longIntro : 这一场酒后抱怨引发的惨案，一个现实与奇幻交织的故事……
     * majorCate : 玄幻
     * minorCate : 东方玄幻
     * cover : /agent/http://img.1391.com/api/v1/bookcenter/cover/1/2194915/2194915_8e7bdbb920754923af6e8e7841aa01db.jpg/
     * hasCopyright : true
     * buytype : 0
     * contentType : txt
     * latelyFollower : 6144
     * wordCount : 410297
     * serializeWordCount : 1875
     * retentionRatio : 26.91
     * updated : 2018-04-16T12:41:15.388Z
     * chaptersCount : 188
     * lastChapter : 第188章 试探
     * isCollect : false
     * rating : {"count":106,"score":7.831,"isEffect":true}
     * tags : []
     * gender : ["male"]
     * copyright : 阅文集团正版授权
     */

    private String _id;
    private String title;
    private String author;
    private String longIntro;
    private String majorCate;
    private String minorCate;
    private String cover;
    private boolean hasCopyright;
    private int buytype;
    private String contentType;
    private int latelyFollower;
    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;
    private String updated;
    private int chaptersCount;
    private String lastChapter;
    private boolean isCollect;
    private RatingBean rating;
    private String copyright;
    private List<String> tags;
    private List<String> gender;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isHasCopyright() {
        return hasCopyright;
    }

    public void setHasCopyright(boolean hasCopyright) {
        this.hasCopyright = hasCopyright;
    }

    public int getBuytype() {
        return buytype;
    }

    public void setBuytype(int buytype) {
        this.buytype = buytype;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public static class RatingBean {
        /**
         * count : 106
         * score : 7.831
         * isEffect : true
         */

        private int count;
        private double score;
        private boolean isEffect;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public boolean isIsEffect() {
            return isEffect;
        }

        public void setIsEffect(boolean isEffect) {
            this.isEffect = isEffect;
        }
    }

}
