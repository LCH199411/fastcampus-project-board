package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // 기본적으로 엔티티안에 있는 모든 필드에 대한 모든 검색 기능 추가를 해준다.
        QuerydslBinderCustomizer<QArticle> { // 검색에 대한 세부적이 규칙 재구성

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) { // 자바 8 이후 인터페이스에서 구현이 가능해짐
        bindings.excludeUnlistedProperties(true); // 선택적으로 검색 가능하게 하기 위해 사용
        bindings.including(root.title, root.hashtag, root.createdAt, root.createdBy);
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase);      // like '${v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // like '%${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // date 타입이므로
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

    }

}