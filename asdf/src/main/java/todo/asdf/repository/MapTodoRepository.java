package todo.asdf.repository;

import todo.asdf.domain.Todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapTodoRepository implements TodoRepository{

    private static long sequence = 0L;
    private static Map<Long, Todo> todoStore = new HashMap<>();

    @Override
    public Todo save(Todo todo) {
        todo.setIndex(sequence++);

        String pattern = "yyyy-MM-dd HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        todo.setLastUpdatedDate(simpleDateFormat.format(new Date()));
        todo.setComplete(false);

        return todo;
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todoStore.values());
    }
}
