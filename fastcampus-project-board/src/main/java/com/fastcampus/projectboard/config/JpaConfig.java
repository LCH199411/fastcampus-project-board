package com.fastcampus.projectboard.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing // Auditing 사용 설정??
@Configurable // 각종 설정 빈들
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() { // jpa auditing
        return () -> Optional.of("uno");    // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자

    }




}
