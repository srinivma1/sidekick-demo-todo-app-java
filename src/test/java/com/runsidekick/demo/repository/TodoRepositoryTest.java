package com.runsidekick.demo.repository;

import com.runsidekick.demo.entity.TodoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * @author tolgatakir
 */

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SqlGroup({
@Sql("/sql/test-data/todo.sql"),
@Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @Test
    void testFindByCompletedIsTrue() {
        List<TodoEntity> actual = repository.findByCompletedIsTrue();
        assertThat(actual).extracting(TodoEntity::getId, TodoEntity::getTitle, TodoEntity::isCompleted)
        .containsExactly(tuple(1L, "Test-1", true), tuple(3L, "Test-3", true))
        .hasSize(2);
    }

}
