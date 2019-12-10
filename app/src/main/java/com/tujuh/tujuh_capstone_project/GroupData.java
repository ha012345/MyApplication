package com.tujuh.tujuh_capstone_project;

public class GroupData {

    private int iv_profile;
    private String tv_name;
    private String tv_content;
    private long tv_count;

    public GroupData(int iv_profile, String tv_name, String tv_content, long tv_count) {
        this.iv_profile = iv_profile;
        this.tv_name = tv_name;
        this.tv_content = tv_content;
        this.tv_count = tv_count;
    }


    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_content() {
        return tv_content;
    }

    public void setTv_content(String tv_content) {
        this.tv_content = tv_content;
    }

    public long getTv_count() {
        return tv_count;
    }

    public void setTv_content(long tv_content) {
        this.tv_count = tv_count;
    }
}