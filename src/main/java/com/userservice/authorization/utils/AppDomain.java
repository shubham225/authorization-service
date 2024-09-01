package com.userservice.authorization.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppDomain {
    private static final AppDomain INSTANCE = new AppDomain();
    private String httpPath;

    private AppDomain() {
    }

    public static AppDomain getInstance() {
        return INSTANCE;
    }
}
