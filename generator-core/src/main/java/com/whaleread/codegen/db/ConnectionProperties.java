package com.whaleread.codegen.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Properties;

/**
 * jdbc connection properties
 * Created by Dolphin on 2018/1/17
 */
@Getter
@Setter
@ToString
public class ConnectionProperties {
    /**
     * jdbc connection url
     */
    private String url;
    /**
     * jdbc connection username
     */
    private String username;
    /**
     * jdbc connection password
     */
    private String password;

    private String driverClass;

    private Properties otherProperties;

    /**
     * init script path
     */
    private String script;
}
