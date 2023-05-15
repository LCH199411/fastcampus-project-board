package com.fastcampus.projectboard.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor    // 확인하고 지우기
@Data
public class ArticleCommentDto {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String content;

    // 확인하고 지우기
    public ArticleCommentDto(LocalDateTime modifiedAt, String modifiedBy, String content) {
    }

    // 확인하기 지우기 , of 메소드 사용
    public static ArticleCommentDto of(LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(modifiedAt,modifiedBy,content);
    }

}
