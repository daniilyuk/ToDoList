package ru.todolist.manager.todolistmanager.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.todolist.manager.todolistmanager.config.Endpoint;
import ru.todolist.manager.todolistmanager.config.RestProperties;

import java.time.Duration;
import java.util.Map;

@Component
@AllArgsConstructor
public class RestClientImpl<I, R>  {

    private final WebClient webClient;

    private final RestProperties restProperties;

    public ResponseEntity<?> sendMessage(RestRequestBuilder<I, R> requestBuilder) {
        if(requestBuilder.getEndpointName()==null){
            throw new NullPointerException("Название endPoint не передано");
        }
        val endpoint = restProperties.getEndpoints().stream()
                .filter(e -> e.getName().equals(requestBuilder.getEndpointName()))
                .findAny()
                .map(Endpoint::getUri)
                .map( u -> {
                    if (requestBuilder.getParams() == null || requestBuilder.getParams().isEmpty()){
                        return u;
                    }
                    return u + getRequestParamsFromMap(requestBuilder.getParams());
                })
                .orElseThrow(IllegalArgumentException::new);


        Mono<ResponseEntity<R>> request = webClient.post()
                .uri(endpoint)
                .contentType(requestBuilder.getMediaType())
                .headers( httpHeaders -> {
                    if (requestBuilder.getHeaders() != null){
                        httpHeaders.setAll(requestBuilder.getHeaders());
                    }
                })
                .body(requestBuilder.getRequest() != null ?
                        BodyInserters.fromValue(requestBuilder.getRequest() ) :
                        BodyInserters.empty())
                .retrieve()
                .toEntity(requestBuilder.getResponseType());

        if (requestBuilder.getTimeout() != 0){
            return request
                    .timeout(Duration.ofMillis(requestBuilder.getTimeout()))
                    .block();
        }

        return request.block();
    }


    private static String getRequestParamsFromMap(Map<String, String> params){
         return "?" + String.join("&", params.entrySet()
                 .stream()
                 .map(el -> "%s=%s".formatted(el.getKey(), el.getValue()))
                 .toList());
    }
}
