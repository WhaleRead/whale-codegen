package com.whaleread.codegen.config;

import java.util.List;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;
import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/1/18
 */
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

    private boolean enableModel = true;
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

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getModelSubPackage() {
        return modelSubPackage;
    }

    public void setModelSubPackage(String modelSubPackage) {
        this.modelSubPackage = modelSubPackage;
    }

    public String getDaoSubPackage() {
        return daoSubPackage;
    }

    public void setDaoSubPackage(String daoSubPackage) {
        this.daoSubPackage = daoSubPackage;
    }

    public String getServiceSubPackage() {
        return serviceSubPackage;
    }

    public void setServiceSubPackage(String serviceSubPackage) {
        this.serviceSubPackage = serviceSubPackage;
    }

    public String getDtoSubPackage() {
        return dtoSubPackage;
    }

    public void setDtoSubPackage(String dtoSubPackage) {
        this.dtoSubPackage = dtoSubPackage;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getServiceSuffix() {
        return serviceSuffix;
    }

    public void setServiceSuffix(String serviceSuffix) {
        this.serviceSuffix = serviceSuffix;
    }

    public String getDtoSuffix() {
        return dtoSuffix;
    }

    public void setDtoSuffix(String dtoSuffix) {
        this.dtoSuffix = dtoSuffix;
    }

    public boolean isEnableModel() {
        return enableModel;
    }

    public void setEnableModel(boolean enableModel) {
        this.enableModel = enableModel;
    }

    public boolean isEnableDTO() {
        return enableDTO;
    }

    public void setEnableDTO(boolean enableDTO) {
        this.enableDTO = enableDTO;
    }

    public boolean isEnableDAO() {
        return enableDAO;
    }

    public void setEnableDAO(boolean enableDAO) {
        this.enableDAO = enableDAO;
    }

    public boolean isEnableService() {
        return enableService;
    }

    public void setEnableService(boolean enableService) {
        this.enableService = enableService;
    }

    public boolean isEnableDAOMethods() {
        return enableDAOMethods;
    }

    public void setEnableDAOMethods(boolean enableDAOMethods) {
        this.enableDAOMethods = enableDAOMethods;
    }

    public boolean isEnableNonNull() {
        return enableNonNull;
    }

    public void setEnableNonNull(boolean enableNonNull) {
        this.enableNonNull = enableNonNull;
    }
}
