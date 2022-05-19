package com.szmirren.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rock
 * @date 2022/2/16 2:25 下午
 * @description
 */
public class PathConfig {
    /**
     * 通用配置
     */
    public static final String FILE_DATE = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
    public static final String i18n_PACKAGE = "com.youzan.i18n";

    /**
     * object
     */
    public static final String ENTITY_PACKAGE = "domain.core.entity";
    public static final String DTO_PACKAGE = "application.dto";
    public static final String DO_PACKAGE = "dal.dataobject";

    public static final String ENTITY_DTO_CONVERTOR = "application.convertor";
    public static final String ENTITY_DO_TRANSLATOR = "translator";

    public static final String DAO_PACKAGE = "dal.dao";

    public static final String REPOSITORY_PACKAGE = "domain.core.repository";
    public static final String REPOSITORY_IMPL_PACKAGE = "repository.impl";

    public static final String APP_SERVICE_PACKAGE = "application";
    public static final String COMMAND_PACKAGE = "application.command";
    public static final String QUERY_PACKAGE = "application.query";


    public static final String CONTROLLER_ADMIN_PARAM = "admin.param";
    public static final String CONTROLLER_ADMIN_VO = "admin.vo";
    public static final String CONTROLLER_ADMIN_CONVERTOR = "admin.convertor";
    public static final String CONTROLLER_ADMIN_UNIT_TEST = "admin";
    public static final String CONTROLLER_ADMIN_CONTROLLER = "admin.web";
    public static final String CONTROLLER_ADMIN_CONTROLLER_IMPL = "admin.web.impl";


    public static final String CONTROLLER_CUSTOMER_PARAM = "controller.param";
    public static final String CONTROLLER_CUSTOMER_VO = "controller.vo";
    public static final String CONTROLLER_CUSTOMER_CONVERTOR = "controller.convertor";
    public static final String CONTROLLER_CUSTOMER_UNIT_TEST = "controller";
    public static final String CONTROLLER_CUSTOMER_CONTROLLER = "controller.web";
    public static final String CONTROLLER_CUSTOMER_CONTROLLER_IMPL = "controller.web.impl";
    /**
     * 对应的应用模块名
     */
    public static final String APPLICATION = "application";
    public static final String CONTROLLER = "controller";
    public static final String CONTROLLER_TEST = "test";
    public static final String DOMAIN = "domain";
    public static final String INFRASTRUCTURE = "infrastructure";

}
