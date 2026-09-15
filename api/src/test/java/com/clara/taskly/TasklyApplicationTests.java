package com.clara.taskly;

import com.clara.taskly.member.MemberRepository;
import com.clara.taskly.task.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/**
 * Smoke-test the Spring context without JPA.
 *
 * <p>{@code Task} and {@code Member} are not {@code @Entity} yet — that is candidate work.
 * Once they are mapped, drop the {@code spring.autoconfigure.exclude} property and the
 * repository mocks; {@code JwtDecoder} should stay mocked so this test stays offline.
 */
@SpringBootTest(properties = {
        "spring.autoconfigure.exclude="
                + "org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration,"
                + "org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration"
})
@ActiveProfiles("test")
class TasklyApplicationTests {

    @MockitoBean JwtDecoder jwtDecoder;
    @MockitoBean MemberRepository memberRepository;
    @MockitoBean TaskRepository taskRepository;

    @Test
    void contextLoads() {
    }
}
