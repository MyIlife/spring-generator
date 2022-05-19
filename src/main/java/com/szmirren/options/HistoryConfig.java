package com.szmirren.options;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 配置信息
 * 
 * @author Mirren
 *
 */
@Data
public class HistoryConfig {
	/** 配置信息的名字 */
	private String historyConfigName;
	/** 生产路径 */
	private String projectPath;
	/** 实体类的包名 */
	private String entityPackage;
	/** 实体类的类名 */
	private String entityName;
	private String DOPackage;
	private String DOName;
	private String DTOPackage;
	private String DTOName;
	/** service包名 */
	private String servicePackage;
	/** service类名 */
	private String serviceName;
	/** service实现类包名 */
	private String serviceImplPackage;
	/** service实现类名 */
	private String serviceImplName;
	/** Controller类包名 */
	private String controllerPackage;
	/** Controller类名 */
	private String controllerName;
	/** Dao类的包 */
	private String daoPackage;
	/** Dao类名 */
	private String daoName;
	/** Mapper类的包 */
	private String mapperPackage;
	/** Mapper名称 */
	private String mapperName;
	/** 单元测试包名 */
	private String unitTestPackage;
	/** 单元测试类名 */
	private String unitTestName;
	/** 字符编码格式 */
	private String codeFormat;

	private String paramPackage;
	private String paramName;

	private String voPackage;
	private String voName;

	private String routerConvertorPackage;
	private String routerConvertName;

	/** 数据库配置文件 */
	private DatabaseConfig dbConfig;
	/** 实体类配置文件 */
	private EntityConfig entityConfig;
	/** DO类配置文件 */
	private EntityConfig DOConfig;
	/** DO类配置文件 */
	private EntityConfig DTOConfig;
	/** Service配置文件 */
	private ServiceConfig serviceConfig;
	/** Service实现类的配置文件 */
	private ServiceImplConfig serviceImplConfig;
	/** b Controller的配置文件 */
	private ControllerConfig controllerConfig;
	private ControllerImplConfig controllerImplConfig;
	/** c Controller的配置文件 */
	private CControllerConfig cControllerConfig;
	private CControllerImplConfig cControllerImplConfig;
	/** DAO的配置文件 */
	private DaoConfig daoConfig;
	/** Mapper的配置文件 */
	private MapperConfig mapperConfig;
	/** 单元测试配置文件 */
	private UnitTestConfig unitTestConfig;
	/** 单元测试配置文件 */
	private CUnitTestConfig cUnitTestConfig;
	/** entity转DO配置 */
	private EntityDOConfig entityDOConfig;
	/** entity转DO配置 */
	private EntityDTOConfig entityDTOConfig;
	/** AppService配置 */
	private AppServiceConfig appServiceConfig;
	/** command配置 */
	private EntityConfig commandConfig;
	/** query配置 */
	private EntityConfig queryConfig;

	/** b端 VO类配置文件 */
	private EntityConfig VOConfig;

	/** b端 Param类配置文件 */
	private EntityConfig ParamConfig;

	private RouterConvertorConfig routerConvertorConfig;

	/** c端 VO类配置文件 */
	private EntityConfig CVOConfig;

	/** c端 Param类配置文件 */
	private EntityConfig CParamConfig;

	private CRouterConvertorConfig cRouterConvertorConfig;

	/** 自定义包类的配置文件 */
	@JSONField(serialize = false, deserialize = false)
	private CustomConfig customConfig;
	@JSONField(name = "customConfig")
	private JSONObject customJsonConfig;
	/** 自定义属性的配置文件 */
	private CustomPropertyConfig customPropertyConfig;

	/**
	 * 初始化
	 */
	public HistoryConfig() {
		super();
	}

	public HistoryConfig(String projectPath,
						 String entityPackage, String entityName,
						 String DOPackage, String DOName,
						 String DTOPackage, String DTOName,
						 String servicePackage, String serviceName,
						 String serviceImplPackage, String serviceImplName,
						 String controllerPackage, String controllerName,
						 String daoPackage, String daoName,
						 String mapperPackage, String mapperName,
						 String unitTestPackage, String unitTestName,
						 String codeFormat,
						 String paramPackage, String paramName,
						 String voPackage, String voName,
						 String routerConvertorPackage, String routerConvertName) {
		this.historyConfigName = historyConfigName;
		this.projectPath = projectPath;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
		this.DOPackage = DOPackage;
		this.DOName = DOName;
		this.DTOPackage = DTOPackage;
		this.DTOName = DTOName;
		this.servicePackage = servicePackage;
		this.serviceName = serviceName;
		this.serviceImplPackage = serviceImplPackage;
		this.serviceImplName = serviceImplName;
		this.controllerPackage = controllerPackage;
		this.controllerName = controllerName;
		this.daoPackage = daoPackage;
		this.daoName = daoName;
		this.mapperPackage = mapperPackage;
		this.mapperName = mapperName;
		this.unitTestPackage = unitTestPackage;
		this.unitTestName = unitTestName;
		this.codeFormat = codeFormat;
		this.paramPackage = paramPackage;
		this.paramName = paramName;
		this.voPackage = voPackage;
		this.voName = voName;
		this.routerConvertorPackage = routerConvertorPackage;
		this.routerConvertName = routerConvertName;
	}
}
