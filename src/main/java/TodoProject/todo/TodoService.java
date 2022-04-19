package TodoProject.todo;


import java.util.List;

public class TodoService {
    private TodoRepository repository;

    TodoService() {
        this(new TodoRepository());
    }

    TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    List<TodoDTO> findAll() {
        return repository.findAll().stream().map(TodoDTO::new).toList();
    }

    public TodoRepository getRepository() {
        return repository;
    }

    public void setRepository(TodoRepository repository) {
        this.repository = repository;
    }
}
