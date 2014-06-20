package com.interzonedev.pretendpoint.web.echo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.interzonedev.pretendpoint.web.PretendPointResponse;

public class EchoResponse extends PretendPointResponse {

    private final String url;

    private final String method;

    private final Map<String, List<String>> parameters;

    private final Map<String, List<String>> headers;

    public EchoResponse(String url, String method, Map<String, List<String>> parameters,
            Map<String, List<String>> headers) {
        this.url = url;
        this.method = method;

        if (null != parameters) {
            this.parameters = Collections.unmodifiableMap(parameters);
        } else {
            this.parameters = Collections.emptyMap();

        }

        if (null != headers) {
            this.headers = Collections.unmodifiableMap(headers);
        } else {
            this.headers = Collections.emptyMap();

        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

}
