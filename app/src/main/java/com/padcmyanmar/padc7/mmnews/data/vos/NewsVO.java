package com.padcmyanmar.padc7.mmnews.data.vos;

import java.util.List;

public class NewsVO {

    private String newsId;
    private String brief;
    private String details;
    private List<String> images;
    private String postedDate;
    private PublicationVO publication;
    private List<FavoriteVO> favorites;

}
