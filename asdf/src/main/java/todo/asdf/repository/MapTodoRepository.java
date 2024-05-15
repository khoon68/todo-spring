package todo.asdf.repository;

import org.springframework.jdbc.core.RowMapper;
import todo.asdf.domain.Todo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapTodoRepository implements TodoRepository{

    private static long sequence = 0L;
    private static Map<Long, Todo> todoStore = new HashMap<>();

    @Override
    public Todo save(Todo todo) {
        todo.setIndex(sequence++);

        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        todo.setLastUpdatedDate(simpleDateFormat.format(timestamp));
        System.out.println(todo.getLastUpdatedDate());
        todo.setComplete(false);

        todoStore.put(sequence, todo);

        return todo;
    }

    @Override
    public List<Todo> findListByMemberId(String memberId) {
        return new ArrayList<>(todoStore.values().stream().filter((todo) -> todo.getMemberId().equals(memberId)).toList());
    }
}
