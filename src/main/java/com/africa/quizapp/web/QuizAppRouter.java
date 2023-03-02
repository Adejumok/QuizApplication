package com.africa.quizapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@RequiredArgsConstructor
public class QuizAppRouter {
    private final QuizAppHandler quizAppHandler;

    @Bean
    public RouterFunction<ServerResponse> quizRouterFunction(){
        return
                route().path("api/v1/quiz", builder -> builder
                        .nest(accept(APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("/", quizAppHandler::addQuiz)
                        )
                ).build();

    }

}
