package com.szmirren.entity;

import lombok.Data;

/**
 * 生成文件的上下文
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
@Data
public class GeneratorContent {

	/** 数据库配置文件 */
	private DatabaseContent database;
	/** 实体类配置信息 */
	private EntityContent entity;
	/** 数据库表的属性 */
	private TableContent table;
	/** 实体类配置信息 */
	private ServiceContent service;
	/** 实体类配置信息 */
	private ServiceImplContent serviceImpl;
	/** 实体类配置信息 */
	private DaoContent dao;
	/** 实体类配置信息 */
	private MapperContent mapper;
	/** 实体类配置信息 */
	private ControllerContent controller;
	/** 实体类配置信息 */
	private UnitTestContent unitTest;
	/** 实体类配置信息 */
	private CustomContent custom;
	/** 实体类配置信息 */
	private CustomPropertyContent customProperty;

	/** DO类配置信息 */
	private EntityContent DO;
	/** DO类配置信息 */
	private EntityContent DTO;
	/** entity/DTO互转类配置信息 */
	private EntityDTOContent entityDTO;
	/** entity/DO互转类配置信息 */
	private EntityDOContent entityDO;
	/** AppService配置信息 */
	private AppServiceContent appService;

	private EntityContent command;
	private EntityContent query;

	private EntityContent param;

	private EntityContent VO;

	private RouterConvertorContent routerConvertor;

	private EntityContent CParam;

	private EntityContent CVO;

	private CRouterConvertorContent CRouterConvertor;

	private CControllerContent CController;

	private CUnitTestContent CUnitTest;

	private ControllerImplContent controllerImpl;

	private CControllerImplContent CControllerImpl;

}
