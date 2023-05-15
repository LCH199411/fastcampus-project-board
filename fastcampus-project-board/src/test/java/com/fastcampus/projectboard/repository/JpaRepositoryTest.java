package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("testdb")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트 상태에서 돌린다 해도 테스트 디비를 돌리지 않고 설정된 걸 쓴다.
// application.yaml 파일에 test.database.replace: none 설정했기 때문에 쓰지 않아도 됨
@DisplayName("JAP 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest { //  수정확인

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        // Given
        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {

        // Given
        long previousCount = articleRepository.count();

        // When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @Test
    @DisplayName("update 테스트")
    void JpaRepositoryTestgivenTestData_whenUpdating_thenWorksFine() {

        Article article = articleRepository.findById(1L).orElseThrow(); // 없으면 예외 던져버리기
        String updateHashtag = "#spirngboot";
        article.setHashtag(updateHashtag);

        // Given
        long previousCount = articleRepository.count();

        // When
        Article savedArticle = articleRepository.saveAndFlush(article); // 세이브와 동시에 플러쉬

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);

    }

    @Test
    @DisplayName("delete 테스트")
    void JpaRepositoryTestgivenTestData_whenDeleting_thenWorksFine() {

        Article article = articleRepository.findById(1L).orElseThrow(); // 없으면 예외 던져버리기
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        long deletedCommentSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount -1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);

    }


}