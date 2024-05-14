package todo.asdf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo.asdf.repository.JdbcTemplateMemberRepository;
import todo.asdf.repository.MemberRepository;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    private final DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
