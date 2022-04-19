package TodoProject.todo;

public class TodoDTO {
    private Integer id;
    private String text;

    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.text = todo.getText();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
