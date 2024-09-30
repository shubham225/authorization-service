package com.userservice.authorization.listener;

import com.userservice.authorization.utils.AppDomain;
import com.userservice.authorization.utils.IpUtils;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        final String host = IpUtils.getHost();

        if(host.isEmpty())
            AppDomain.getInstance().setHttpPath("http://" + String.join(":", "localhost", String.valueOf(port)));
        else
            AppDomain.getInstance().setHttpPath("http://" + String.join(":", host, String.valueOf(port)));
    }
}
