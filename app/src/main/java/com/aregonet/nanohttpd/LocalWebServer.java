package com.aregonet.nanohttpd;

import android.content.Context;

import java.io.InputStream;

import fi.iki.elonen.NanoHTTPD;

public class LocalWebServer extends NanoHTTPD {
    private  final Context context;

    public LocalWebServer(Context ctx, int port) {
        super(port);
        this.context = ctx;

    }
    @Override
    public  Response serve(IHTTPSession session) {
        String uri = session.getUri();

        if (uri.equals("/")) {
            uri = "/index.html";
        }
        try {
            InputStream inputStream = context.getAssets().open(uri.substring(1));
            String mime = getMimeType(uri);
            return newFixedLengthResponse(Response.Status.OK, mime, inputStream, inputStream.available());
        } catch (Exception e) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Archivo no encontrado:" + uri);
        }
    }
        private String getMimeType(String uri) {
            if (uri.endsWith(".html")) return "text/html";
            if (uri.endsWith(".js")) return "application/javascript";
            if (uri.endsWith(".css")) return "text/css";
            if (uri.endsWith(".json")) return "application/json";
            if (uri.endsWith(".svg")) return "image/svg";
            if (uri.endsWith(".png")) return "image/png";
            if (uri.endsWith(".jpg")) return "image/jpg";
            if (uri.endsWith(".jpeg")) return "image/jpeg";
            if (uri.endsWith(".mp3")) return "audio/mp3";


            return "text/plain";
        }

}
