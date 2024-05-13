package todo.asdf.repository;

import todo.asdf.domain.Todo;

import java.util.List;

public interface TodoRepository {
    Todo save(Todo todo);
    List<Todo> findAll();
}
