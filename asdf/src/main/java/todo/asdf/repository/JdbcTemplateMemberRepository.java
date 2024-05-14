package todo.asdf.repository;

import jakarta.annotation.PreDestroy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import todo.asdf.domain.Member;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Member");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("password", member.getPassword());

        simpleJdbcInsert.execute(parameters);

        return member;
    }

    @Override
    public Member findById(String id) {
        List<Member> result = jdbcTemplate.query("select * from Member where id = ?", memberRowMapper(), id);

        return result.get(0);
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from Member", memberRowMapper());
    }

    @PreDestroy
    public void destory() {
        jdbcTemplate.execute("delete * from Member");
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum) -> {
            Member member = new Member();
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
