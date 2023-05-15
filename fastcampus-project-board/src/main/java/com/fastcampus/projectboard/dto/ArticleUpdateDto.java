package com.fastcampus.projectboard.dto;

import lombok.Data;

@Data
public class ArticleUpdateDto {

    private String title;
    private String content;
    private String hashtag;


    public ArticleUpdateDto(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

}
