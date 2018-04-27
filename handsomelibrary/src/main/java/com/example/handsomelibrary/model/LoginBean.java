package com.example.handsomelibrary.model;

import java.util.List;

/**
 * Created by Stefan on 2018/4/24.
 */

public class LoginBean {

    /**
     * _id : 5add76a9bd088766de30f09b
     * name : 520
     * nickname : WeYue-520
     * password : fd97fedb25ba993e634fa40daa122e41
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiNTIwIiwiaWF0IjoxNTI0Nzk3NjM3LCJleHAiOjE1MjczODk2Mzd9.OycIhpOGZjmPLp2te5kAHZhARO_LCtdUC9C4LLG619U
     * icon : /images/avatar/default_avatar.jpg
     * brief : To be. or not to be. that is the question.
     * likebooks : [{"_id":"5a9628999ccdb50100701710","title":"奈格里之魂","author":"虚鸣","longIntro":"奈格里之魂、复仇之灵、灾���之主、逆神者、原罪等等，这是一个属于残魂的赞歌！穿越一个到低魔世界，被金手指系统骗走主角光环怎么办？在线等！挺急的！！ps1：虚鸣已有150万字完本作品《旧日篇章》，请放心阅读ps2：本文无女主ps3：书友群：578342111","cover":"/agent/http://img.1391.com/api/v1/bookcenter/cover/1/2232189/2232189_58bac2303938404d9d3cb17fb066895b.jpg/","majorCate":"奇幻","minorCate":"西方奇幻","hasCopyright":true,"buytype":0,"contentType":"txt","latelyFollower":3200,"wordCount":311097,"serializeWordCount":5346,"retentionRatio":"55.12","updated":"2018-04-17T10:14:16.118Z","chaptersCount":150,"lastChapter":"第148章 原址","copyright":"阅文集团正版授权","__v":0,"isCollect":false,"rating":{"count":59,"score":7.683,"isEffect":true},"tags":["无限流","奇幻","冷酷","位面"],"gender":["male"]}]
     */

    private String _id;
    private String name;
    private String nickname;
    private String password;
    private String token;
    private String icon;
    private String brief;
    private List<LikebooksBean> likebooks;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<LikebooksBean> getLikebooks() {
        return likebooks;
    }

    public void setLikebooks(List<LikebooksBean> likebooks) {
        this.likebooks = likebooks;
    }

    public static class LikebooksBean {
        /**
         * _id : 5a9628999ccdb50100701710
         * title : 奈格里之魂
         * author : 虚鸣
         * longIntro : 奈格里之魂、复仇之灵、灾���之主、逆神者、原罪等等，这是一个属于残魂的赞歌！穿越一个到低魔世界，被金手指系统骗走主角光环怎么办？在线等！挺急的！！ps1：虚鸣已有150万字完本作品《旧日篇章》，请放心阅读ps2：本文无女主ps3：书友群：578342111
         * cover : /agent/http://img.1391.com/api/v1/bookcenter/cover/1/2232189/2232189_58bac2303938404d9d3cb17fb066895b.jpg/
         * majorCate : 奇幻
         * minorCate : 西方奇幻
         * hasCopyright : true
         * buytype : 0
         * contentType : txt
         * latelyFollower : 3200
         * wordCount : 311097
         * serializeWordCount : 5346
         * retentionRatio : 55.12
         * updated : 2018-04-17T10:14:16.118Z
         * chaptersCount : 150
         * lastChapter : 第148章 原址
         * copyright : 阅文集团正版授权
         * __v : 0
         * isCollect : false
         * rating : {"count":59,"score":7.683,"isEffect":true}
         * tags : ["无限流","奇幻","冷酷","位面"]
         * gender : ["male"]
         */

        private String _id;
        private String title;
        private String author;
        private String longIntro;
        private String cover;
        private String majorCate;
        private String minorCate;
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
        private String copyright;
        private int __v;
        private boolean isCollect;
        private RatingBean rating;
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
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
             * count : 59
             * score : 7.683
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
}
