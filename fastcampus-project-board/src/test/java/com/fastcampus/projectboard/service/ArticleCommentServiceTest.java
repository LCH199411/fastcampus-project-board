package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        // given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title","content","#java")));
        // when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    // 저장
    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다")
    @Test
    void given_when_then_save() {
        // given
        ArticleCommentDto dto = ArticleCommentDto.of(LocalDateTime.now(), "Uno","saveContent");
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);  // Mockito 라이브러리 사용

        // when
        sut.saveArticleComment(dto);

        // then
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    // 수정
    @DisplayName("게시글 ID와 댓글수정 정보를 입력하면 , 댓글을 수정한다")
    @Test
    void given_when_then_update() {
        // given
        ArticleCommentDto dto = ArticleCommentDto.of(LocalDateTime.now(),"uno2","updateContent");
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // when
        sut.updateArticleComment(1L, dto);

        // then
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    // 삭제
    @DisplayName("게시글 ID를 입력하면, 댓글을 삭제한다")
    @Test
    void given_when_then_delete() {

        // given
        willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class)); // Mockito 라이브러리 사용 , willDoNothing() 쓰는이유 찾아보기
        // when
        sut.deleteArticleComment(1L);
        // then
        then(articleCommentRepository).should().delete(any(ArticleComment.class));
    }



}