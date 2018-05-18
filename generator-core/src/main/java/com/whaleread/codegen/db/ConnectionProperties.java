package com.whaleread.codegen.db;

import java.util.Properties;

/**
 * jdbc connection properties
 * Created by Dolphin on 2018/1/17
 */
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public Properties getOtherProperties() {
        return otherProperties;
    }

    public void setOtherProperties(Properties otherProperties) {
        this.otherProperties = otherProperties;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
