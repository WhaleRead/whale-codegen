package com.whaleread.codegen.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;
import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/1/18
 */
@Getter
@Setter
public class BuiltInGeneratorConfiguration extends PropertyHolder {
    private String targetPackage;

    private String targetProject;

    private String modelSubPackage = "model";
    private String daoSubPackage = "repository";
    private String serviceSubPackage = "service";
    private String dtoSubPackage = "dto";

    private String daoSuffix = "Repository";
    private String serviceSuffix = "Service";
    private String dtoSuffix = "DTO";

    private boolean enableDTO;
    private boolean enableDAO;
    private boolean enableService;
    private boolean enableDAOMethods;

    /**
     * Use Optional wrap return value where may be null
     */
    private boolean enableNonNull;

    public void validate(List<String> errors, String contextId) {
        if (!stringHasValue(targetProject)) {
            errors.add(getString("ValidationError.2", contextId)); //$NON-NLS-1$
        }
        if (!stringHasValue(targetPackage)) {
            errors.add(getString("ValidationError.12", //$NON-NLS-1$
                    "javaClientGenerator", contextId)); //$NON-NLS-1$
        }
    }
}
