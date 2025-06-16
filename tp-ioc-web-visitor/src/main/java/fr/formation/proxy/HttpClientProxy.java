package fr.formation.proxy;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;

@Component
public class HttpClientProxy extends HttpClient {
    @Inject
    private HttpClient httpClient;

    private Map<String, HttpResponse<?>> cache = new HashMap<>();

    @Override
    public Optional<CookieHandler> cookieHandler() {
        return this.httpClient.cookieHandler();
    }
    
    @Override
    public Optional<Duration> connectTimeout() {
        return this.httpClient.connectTimeout();
    }

    @Override
    public Redirect followRedirects() {
        return this.httpClient.followRedirects();
    }

    @Override
    public Optional<ProxySelector> proxy() {
        return this.httpClient.proxy();
    }

    @Override
    public SSLContext sslContext() {
        return this.httpClient.sslContext();
    }

    @Override
    public SSLParameters sslParameters() {
        return this.httpClient.sslParameters();
    }

    @Override
    public Optional<Authenticator> authenticator() {
        return this.httpClient.authenticator();
    }

    @Override
    public Version version() {
        return this.httpClient.version();
    }

    @Override
    public Optional<Executor> executor() {
        return this.httpClient.executor();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        String url = request.uri().toString();

        if (this.cache.containsKey(url)) {
            return (HttpResponse<T>) this.cache.get(url);
        }

        System.out.println("Le cache n'existe pas ...");

        HttpResponse<T> response = this.httpClient.send(request, responseBodyHandler);

        this.cache.put(url, response);

        return response;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler) {
        return this.httpClient.sendAsync(request, responseBodyHandler);
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler, PushPromiseHandler<T> pushPromiseHandler) {
        return this.httpClient.sendAsync(request, responseBodyHandler, pushPromiseHandler);
    }
}
