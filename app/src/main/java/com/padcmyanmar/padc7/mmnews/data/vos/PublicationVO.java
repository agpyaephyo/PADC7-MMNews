package com.padcmyanmar.padc7.mmnews.data.vos;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class PublicationVO {

    @SerializedName("publication-id")
    @ColumnInfo(name = "publication_id")
    private String publicationId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "logo")
    @SerializedName("logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
