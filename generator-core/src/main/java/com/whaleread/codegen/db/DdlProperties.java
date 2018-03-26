package com.whaleread.codegen.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Dolphin on 2018/1/17
 */
@Getter
@Setter
@ToString
public class DdlProperties {
    private String type;
    private String path;
    private String catalog;
    private String schema;
    private String charset = "UTF-8";
}
