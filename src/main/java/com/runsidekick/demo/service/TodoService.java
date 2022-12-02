package com.runsidekick.demo.service;

import com.runsidekick.demo.model.Todo;

import java.util.List;

/**
 * @author tolgatakir
 */
public interface TodoService {
    List<Todo> findTodos();

    Todo addTodo(Todo todo);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodo(Long id);

    Todo duplicateTodo(Long id);

    void clearCompletedTodo();
}
