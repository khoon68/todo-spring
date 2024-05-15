package todo.asdf.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import todo.asdf.domain.Todo;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateTodoRepository implements TodoRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public List<Todo> findListByMemberId(String memberID) {
        return null;
    }

    static RowMapper<Todo> todoRowMapper() {
        return ((rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setIndex(rs.getLong("index"));
            todo.setMemberId(rs.getString("memberId"));
            todo.setContent(rs.getString("content"));
            todo.setLastUpdatedDate(rs.getTimestamp("lastUpdatedDate"));
            return todo;
        });
    }
}
