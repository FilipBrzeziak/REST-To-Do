package TodoProject.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Todo", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private TodoService service;
    private ObjectMapper mapper;


    //Servlet container needs it
    @SuppressWarnings("unused")
    public TodoServlet() {
        this(new TodoService(), new ObjectMapper());
    }

    TodoServlet(TodoService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters  " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), service.findAll());
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var path = String.valueOf(req.getPathInfo());
        try {
            var toDoId = Integer.valueOf(path.substring(1));
            var todo = service.getRepository().toggleTodo(toDoId);
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), todo);
        } catch (Exception e) {
            logger.warn("Wrong path used: " + path);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        var todo = mapper.readValue(req.getInputStream(), Todo.class);
        mapper.writeValue(resp.getOutputStream(), service.getRepository().addTodo(todo));
    }
}
