package ru.todolist.manager.todolistmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import ru.todolist.manager.todolistmanager.entity.Task;
import ru.todolist.manager.todolistmanager.rest.RestClientImpl;
import ru.todolist.manager.todolistmanager.rest.RestRequestBuilder;

@SpringBootApplication
public class ToDoLIstManagerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(ToDoLIstManagerApplication.class, args);
        RestClientImpl<String, Task> bean = run.getBean(RestClientImpl.class);
        ResponseEntity<?> responseEntity = bean.sendMessage(
                new RestRequestBuilder<String, Task>(Task.class)
                        .endpointName("create-task")
                        .request("""
                                {
                                    "title": "fds",
                                    "description": "gfsdgds",
                                    "date": "2024-11-23",
                                    "completed": true
                                }
                                """)
                        .build()
        );
        System.out.println(responseEntity.getBody());
    }

}
