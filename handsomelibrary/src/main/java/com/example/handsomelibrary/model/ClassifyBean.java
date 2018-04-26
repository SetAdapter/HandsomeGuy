package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2018/4/26 15:54.
 */

public class ClassifyBean implements Serializable{

    public static class DataBean implements Serializable{
        private List<MaleBean> male;
        private List<FemaleBean> female;
        private List<PressBean> press;

        public List<MaleBean> getMale() {
            return male;
        }

        public void setMale(List<MaleBean> male) {
            this.male = male;
        }

        public List<FemaleBean> getFemale() {
            return female;
        }

        public void setFemale(List<FemaleBean> female) {
            this.female = female;
        }

        public List<PressBean> getPress() {
            return press;
        }

        public void setPress(List<PressBean> press) {
            this.press = press;
        }

        public static class MaleBean implements Serializable{
            /**
             * icon : /images/icon/玄幻.png
             * monthlyCount : 14297
             * bookCount : 491899
             * name : 玄幻
             */

            private String icon;
            private int monthlyCount;
            private int bookCount;
            private String name;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getMonthlyCount() {
                return monthlyCount;
            }

            public void setMonthlyCount(int monthlyCount) {
                this.monthlyCount = monthlyCount;
            }

            public int getBookCount() {
                return bookCount;
            }

            public void setBookCount(int bookCount) {
                this.bookCount = bookCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class FemaleBean implements Serializable{
            /**
             * icon : /images/icon/古代言情.png
             * monthlyCount : 9247
             * bookCount : 459198
             * name : 古代言情
             */

            private String icon;
            private int monthlyCount;
            private int bookCount;
            private String name;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getMonthlyCount() {
                return monthlyCount;
            }

            public void setMonthlyCount(int monthlyCount) {
                this.monthlyCount = monthlyCount;
            }

            public int getBookCount() {
                return bookCount;
            }

            public void setBookCount(int bookCount) {
                this.bookCount = bookCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class PressBean implements Serializable{
            /**
             * icon : /images/icon/传记名著.png
             * monthlyCount : 1387
             * bookCount : 4280
             * name : 传记名著
             */

            private String icon;
            private int monthlyCount;
            private int bookCount;
            private String name;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getMonthlyCount() {
                return monthlyCount;
            }

            public void setMonthlyCount(int monthlyCount) {
                this.monthlyCount = monthlyCount;
            }

            public int getBookCount() {
                return bookCount;
            }

            public void setBookCount(int bookCount) {
                this.bookCount = bookCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
