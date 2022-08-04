package com.angle.lib_net;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

class PrintingEventListener extends EventListener {
    static final Factory FACTORY = new Factory() {
        @NonNull
        @Override
        public EventListener create(@NonNull Call call) {
            long callId = nextCallId.getAndIncrement();
            System.out.printf("%04d %s%n", callId, call.request().url());
            return new PrintingEventListener(callId, System.nanoTime());
        }

        final AtomicLong nextCallId = new AtomicLong(1L);

    };

    final long callId;
    final long callStartNanos;

    PrintingEventListener(long callId, long callStartNanos) {
        this.callId = callId;
        this.callStartNanos = callStartNanos;
    }

    private void printEvent(String name) {
        long elapsedNanos = System.nanoTime() - callStartNanos;
        System.out.printf("%04d %.3f %s%n", callId, elapsedNanos / 1000000000d, name);
    }

    @Override
    public void callStart(@NonNull Call call) {
        printEvent("callStart");
    }

    @Override
    public void dnsStart(@NonNull Call call, @NonNull String domainName) {
        printEvent("dnsStart");
    }

    @Override
    public void dnsEnd(@NonNull Call call, @NonNull String domainName, @NonNull List<InetAddress> inetAddressList) {
        printEvent("dnsEnd");
    }

    @Override
    public void connectStart(
            @NonNull Call call, @NonNull InetSocketAddress inetSocketAddress, @NonNull Proxy proxy) {
        printEvent("connectStart");
    }

    @Override
    public void secureConnectStart(@NonNull Call call) {
        printEvent("secureConnectStart");
    }

    @Override
    public void secureConnectEnd(@NonNull Call call, Handshake handshake) {
        printEvent("secureConnectEnd");
    }

    @Override
    public void connectEnd(
            @NonNull Call call, @NonNull InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        printEvent("connectEnd");
    }

    @Override
    public void connectFailed(@NonNull Call call, @NonNull InetSocketAddress inetSocketAddress, @NonNull Proxy proxy,
                              Protocol protocol, @NonNull IOException ioe) {
        printEvent("connectFailed");
    }

    @Override
    public void connectionAcquired(@NonNull Call call, Connection connection) {
        printEvent("connectionAcquired");

        String hostAddress = connection.socket().getInetAddress().getHostAddress();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) connection.socket().getRemoteSocketAddress();


        printEvent("connectionAcquired     " + hostAddress);
        printEvent("connectionAcquired     "+ inetSocketAddress.getHostString()+"=========" + inetSocketAddress.getAddress().getHostAddress());
    }

    @Override
    public void connectionReleased(@NonNull Call call, @NonNull Connection connection) {
        printEvent("connectionReleased");
    }

    @Override
    public void requestHeadersStart(@NonNull Call call) {
        printEvent("requestHeadersStart");
    }

    @Override
    public void requestHeadersEnd(@NonNull Call call, @NonNull Request request) {
        printEvent("requestHeadersEnd");
    }

    @Override
    public void requestBodyStart(@NonNull Call call) {
        printEvent("requestBodyStart");
    }

    @Override
    public void requestBodyEnd(@NonNull Call call, long byteCount) {
        printEvent("requestBodyEnd");
    }

    @Override
    public void responseHeadersStart(@NonNull Call call) {
        printEvent("responseHeadersStart");
    }

    @Override
    public void responseHeadersEnd(@NonNull Call call, @NonNull Response response) {
        printEvent("responseHeadersEnd");
    }

    @Override
    public void responseBodyStart(@NonNull Call call) {
        printEvent("responseBodyStart");
    }

    @Override
    public void responseBodyEnd(@NonNull Call call, long byteCount) {
        printEvent("responseBodyEnd");
    }

    @Override
    public void callEnd(@NonNull Call call) {
        printEvent("callEnd");
    }

    @Override
    public void callFailed(@NonNull Call call, @NonNull IOException ioe) {
        printEvent("callFailed");
    }

}