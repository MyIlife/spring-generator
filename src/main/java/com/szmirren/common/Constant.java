package com.szmirren.common;

import com.szmirren.Main;

/**
 * 工具需要用到的常量词
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public interface Constant {
	// 数据库名字
	/** MySQL */
	static final String MYSQL = "MySQL";
	/** PostgreSQL */
	static final String POSTGRE_SQL = "PostgreSQL";
	/** SqlServer */
	static final String SQL_SERVER = "SqlServer";
	/** Oracle */
	static final String ORACLE = "Oracle";
	/** Oracle */
	static final String SQLITE = "Sqlite";

	/** java的后缀名.java */
	static final String JAVA_SUFFIX = ".java";

	/** default */
	static final String DEFAULT = "default";
	/** language */
	static final String LANGUAGE = "language";
	/** 模板的文件夹名称 */
	static final String TEMPLATE_DIR_NAME = "template";
	/** 刷新模板文件夹 */
	static final String TEMPLATE_DIR_REFRESH = "刷新模板文件夹";
	/** 实体类模板的默认名字 */
	static final String TEMPLATE_NAME_ENTITY = "Entity.ftl";
	/** DO类模板的默认名字 */
	static final String TEMPLATE_NAME_DO = "DO.ftl";
	/** admin VO类模板的默认名字 */
	static final String TEMPLATE_NAME_VO = "VO.ftl";
	static final String TEMPLATE_NAME_C_VO = "CVO.ftl";
	/** admin param类模板的默认名字 */
	static final String TEMPLATE_NAME_PARAM = "Param.ftl";
	static final String TEMPLATE_NAME_C_PARAM = "CParam.ftl";
	/** DTO类模板的默认名字 */
	static final String TEMPLATE_NAME_DTO = "DTO.ftl";
	/** command类模板的默认名字 */
	static final String TEMPLATE_NAME_COMMAND = "Command.ftl";
	/** query类模板的默认名字 */
	static final String TEMPLATE_NAME_QUERY = "Query.ftl";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE = "Service.ftl";
	/** ServiceImpl模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE_IMPL = "ServiceImpl.ftl";
	/** AppService模板的默认名字 */
	static final String TEMPLATE_NAME_APP_SERVICE_IMPL = "AppService.ftl";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_ROUTER = "Controller.ftl";
	static final String TEMPLATE_NAME_ROUTER_IMPL = "ControllerImpl.ftl";
	static final String TEMPLATE_NAME_ROUTER_C = "CController.ftl";
	static final String TEMPLATE_NAME_ROUTER_IMPL_C = "CControllerImpl.ftl";
	/** Dao模板的默认名字 */
	static final String TEMPLATE_NAME_DAO = "Dao.ftl";
	/** Mapper模板的默认名字 */
	static final String TEMPLATE_NAME_MAPPER = Main.LANGUAGE.get(LanguageKey.SET_ABSTRACT_AUTOMATIC).get();
	/** Mapper模板的默认名字 */
	static final String TEMPLATE_NAME_MAPPER_SUFFIX = "Mapper.ftl";
	/** SqlAssist模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_ASSIST = "SqlAssist.ftl";
	/** b 单元测试模板的默认名字 */
	static final String TEMPLATE_NAME_UNIT_TEST = "UnitTest.ftl";
	/** c 单元测试模板的默认名字 */
	static final String TEMPLATE_NAME_UNIT_TEST_C = "CUnitTest.ftl";
	/** Entity/DO的默认名字 */
	static final String TEMPLATE_NAME_ENTITY_DO = "EntityDOTranslator.ftl";
	/** Entity/DTO的默认名字 */
	static final String TEMPLATE_NAME_ENTITY_DTO = "EntityDTOConvertor.ftl";

	static final String TEMPLATE_NAME_ROUTER_CONVERTER = "RouterConvertor.ftl";

	static final String TEMPLATE_NAME_ROUTER_C_CONVERTER = "CRouterConvertor.ftl";
}
