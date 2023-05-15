package com.fastcampus.projectboard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private LocalDateTime createdAt;
    private String createdBy;
    private String title;
    private String content;
    private String hashtag;

    public ArticleDto(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

}
