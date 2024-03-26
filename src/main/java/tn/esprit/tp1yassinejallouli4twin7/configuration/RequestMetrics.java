package tn.esprit.tp1yassinejallouli4twin7.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMetrics {

    private final Counter getRequestCounter;
    private final Counter postRequestCounter;

    @Autowired
    public RequestMetrics(MeterRegistry registry) {
        this.getRequestCounter = Counter.builder("http_requests_get")
                .description("Total number of GET requests")
                .register(registry);
        this.postRequestCounter = Counter.builder("http_requests_post")
                .description("Total number of POST requests")
                .register(registry);
    }

    public void incrementGetRequest() {
        getRequestCounter.increment();
    }

    public void incrementPostRequest() {
        postRequestCounter.increment();
    }
}
