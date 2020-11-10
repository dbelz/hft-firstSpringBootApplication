package me.belz.firstSpringProject;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@RestController
public class FirstSpringProjectApplication {

	private Logger logger = LoggerFactory.getLogger(FirstSpringProjectApplication.class);
	private ArrayList<String> todos = new ArrayList<String>();

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringProjectApplication.class, args);
	}

	// ------------------------------------------------------------------------

	@RequestMapping("/hello")
	public String hello() {
		logger.info("hello() - BEGIN");
		logger.info("hello() - END");
		return "Hello unknown guest! :)";
	}

	// ------------------------------------------------------------------------

	@RequestMapping(value="/hello/{name}", method=RequestMethod.GET)
	public String helloName(@PathVariable String name) {
		logger.info("helloName(" + name + (") - BEGIN"));
		logger.info("helloName(" + name + (") - END"));
		return "Hello " + name + "! :)";
	}

	// ------------------------------------------------------------------------

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String listAllTodos() {
		logger.info("listAllTodos() - BEGIN");
		StringBuilder todos = buildTodoListString();
		logger.info("listAllTodos() - END");
		return todos.toString();
	}

	// ------------------------------------------------------------------------

	@RequestMapping(value="/todo/{todo}", method=RequestMethod.PUT)
	public String addTodo(@PathVariable String todo) {
		logger.info("addTodo('" + todo + "') - BEGIN");
		todos.add(todo);
		StringBuilder todos = buildTodoListString();
		logger.info("addTodo('" + todo + "') - END");
		return todos.toString();
	}

	// ------------------------------------------------------------------------

	@RequestMapping(value="/todo/{todo}", method=RequestMethod.DELETE)
	public String removeTodo(@PathVariable String todo) {
		logger.info("removeTodo('" + todo + "') - BEGIN");
		todos.remove(todo);
		StringBuilder todos = buildTodoListString();
		logger.info("removeTodo('" + todo + "') - END");
		return todos.toString();
	}

	// ------------------------------------------------------------------------

	private StringBuilder buildTodoListString() {

		logger.info("buildTodoListString() - BEGIN");
		StringBuilder todos = new StringBuilder();
		for (String todo : this.todos) {
			todos.append(todo).append("\n");
		}
		logger.debug("Current todos:\n" + todos.toString());
		logger.info("buildTodoListString() - END");
		return todos;

	}


}
