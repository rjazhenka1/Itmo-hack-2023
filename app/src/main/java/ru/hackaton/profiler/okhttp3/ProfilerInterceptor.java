package ru.hackaton.profiler.okhttp3;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ProfilerInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        System.out.println("Start request");
        Request request = chain.request();
        System.out.println("End request");
        return chain.proceed(request);
    }
}
