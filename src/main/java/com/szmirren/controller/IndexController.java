package com.szmirren.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.szmirren.entity.*;
import com.szmirren.options.*;
import com.szmirren.utils.FileUtil;
import org.apache.log4j.Logger;

import com.szmirren.Main;
import com.szmirren.common.ConfigUtil;
import com.szmirren.common.Constant;
import com.szmirren.common.ConverterUtil;
import com.szmirren.common.CreateFileUtil;
import com.szmirren.common.DBUtil;
import com.szmirren.common.LanguageKey;
import com.szmirren.common.StrUtil;
import com.szmirren.models.TableAttributeEntity;
import com.szmirren.models.TableAttributeKeyValueTemplate;
import com.szmirren.view.AlertUtil;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

/**
 * 首页的控制器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class IndexController extends BaseController {

	private static final int ALL_FILE_NUM = 26;

	@FXML
	public Label lblDOPackage;
	@FXML
	public TextField txtDOPackage;
	@FXML
	public Label lblDOName;
	@FXML
	public TextField txtDOName;
	@FXML
	public Button btnSetDO;
	@FXML
	public Label lblDTOPackage;
	@FXML
	public TextField txtDTOPackage;
	@FXML
	public Label lblDTOName;
	@FXML
	public TextField txtDTOName;
	@FXML
	public Button btnSetDTO;
	@FXML
	public Label lblEntityRelation;
	@FXML
	public Label lblServiceRelation;
	@FXML
	public Label lblEntityDOPackage;
	public Label lblEntityDTOPackage;
	public TextField txtEntityDOPackage;
	public Label lblEntityDOName;
	public TextField txtEntityDOName;
	public Button btnSetEntityDO;
	public TextField txtEntityDTOPackage;
	public Label lblEntityDTOName;
	public TextField txtEntityDTOName;
	public Button btnSetEntityDTO;
	public Label lblAppServicePackage;
	public TextField txtAppServicePackage;
	public Label lblAppServiceName;
	public TextField txtAppServiceName;
	public Label lblCommandPackage;
	public TextField txtCommandPackage;
	public Label lblCommandName;
	public TextField txtCommandName;
	public Label lblQueryPackage;
	public TextField txtQueryPackage;
	public Label lblQueryName;
	public TextField txtQueryName;
	public TextField txtParamPackage;
	public TextField txtParamName;
	public TextField txtVoPackage;
	public TextField txtVoName;
	public TextField txtRouterConvertorPackage;
	public TextField txtRouterConvertorName;
	public TextField txtRouterImplName;
	public TextField txtRouterImplPackage;
	public Button btnSetAppService;
	public Label lblRouterImplPackage;
	public Label lblRouterImplName;
	public Label lblControllerRelation;
	public Label lblDaoRelation;
	public Label lblParamPackage;
	public Label lblParamName;
	public Label lblVoPackage;
	public Label lblEntityName2;
	public Label lblVoPackage1;
	public Label lblVoPackage11;
	public Label lblControllerRelation1;
	public Label lblRouterPackage1;
	public TextField txtCRouterPackage;
	public Label lblRouterName1;
	public TextField txtCRouterName;
	public Label lblRouterImplPackage1;
	public TextField txtCRouterImplPackage;
	public Label lblRouterImplName1;
	public TextField txtCRouterImplName;
	public Label lblParamPackage1;
	public Label lblVoPackage2;
	public Label lblVoPackage12;
	public Label lblUnitTestPackage1;
	public Label lblParamName1;
	public Label lblEntityName21;
	public Label lblVoPackage111;
	public Label lblUnitTestName1;
	public TextField txtCParamPackage;
	public TextField txtCParamName;
	public TextField txtCVoName;
	public TextField txtCRouterConvertorName;
	public TextField txtCUnitTestName;
	public TextField txtCVoPackage;
	public TextField txtCRouterConvertorPackage;
	public TextField txtCUnitTestPackage;
	public Button btnSetParam;
	public Button btnSetVO;
	public Button btnSetCRouter;
	public Button btnSetCParam;
	public Button btnSetCVO;

	/** DO类名默认的占位符 */
	private String DONamePlace;

	/** DTO类名默认的占位符 */
	private String DTONamePlace;

	private String entityPackage;
	private String DOPackage;
	private String DTOPackage;
	private String sqlPackage;
	private String mapperPackage;
	private String servicePackage;
	private String serviceImplPackage;

	private String entityDTOPackage;
	private String entityDOPackage;

	private String appServicePackage;

	private String queryPackage;
	private String commandPackage;

	private String paramPackage;

	private String voPackage;

	private String routerConvertorPackage;

	private String routerPackage;

	private String routerImplPackage;

	private String unitTestPackage;

	private String cParamPackage;

	private String cVoPackage;

	private String cRouterConvertorPackage;

	private String cRouterPackage;

	private String cRouterImplPackage;

	private String cUnitTestPackage;


	private Logger LOG = Logger.getLogger(this.getClass());
	/** 配置信息的名字 */
	private String historyConfigName;
	/** 程序的配置信息 */
	private HistoryConfig historyConfig;
	/** 模板文件夹中模板现有模板名字 */
	private List<String> templateNameItems;

	/** 存储数据库指定数据库,修改属性时用 */
	private DatabaseConfig selectedDatabaseConfig;
	private DatabaseConfig updateOfDatabaseConfig;

	/** 记录存储的表名,修改属性时用 */
	private String selectedTableName;

	/** 实体类名默认的占位符 */
	private String entityNamePlace;
	/** Service默认占位符 */
	private String serviceNamePlace;
	/** ServiceImpl默认占位符 */
	private String serviceImplNamePlace;
	/** b Controller默认占位符 */
	private String routerNamePlace;
	/** b Controller默认占位符 */
	private String routerImplNamePlace;
	/** c Controller默认占位符 */
	private String cRouterNamePlace;
	/** c Controller默认占位符 */
	private String cRouterImplNamePlace;
	/** Dao默认占位符 */
	private String daoNamePlace;
	/** Mapper默认占位符 */
	private String mapperNamePlace;
	/** b单元测试默认占位符 */
	private String unitTestPlace;

	/** c单元测试默认占位符 */
	private String cUnitTestPlace;

	private String entityDTONamePlace;
	private String entityDONamePlace;
	private String appServiceNamePlace;

	private String commandNamePlace;

	private String queryNamePlace;

	private String paramNamePlace;
	private String voNamePlace;
	private String routerConvertorNamePlace;

	private String cParamNamePlace;
	private String cVoNamePlace;
	private String cRouterConvertorNamePlace;
	// ========================fxml控件============================
	/** 数据库连接 */
	@FXML
	private Label lblConnection;
	/** 配置信息 */
	@FXML
	private Label lblConfig;
	/** 使用说明 */
	@FXML
	private Label lblInstructions;
	/** 设置 */
	@FXML
	private Label lblSetting;
	/** 存放目录 */
	@FXML
	private Label lblProjectPath;
	/** 数据库表名 */
	@FXML
	private Label lblTableName;
	/** 项目名 */
	@FXML
	public Label lblProjectName;
	/** 实体类包名 */
	@FXML
	private Label lblEntityPackage;
	/** Service包名 */
	@FXML
	private Label lblServicePackage;
	/** ServiceImpl包名 */
	@FXML
	private Label lblServiceImplPackage;
	/** router包名 */
	@FXML
	private Label lblRouterPackage;
	/** SQL包名 */
	@FXML
	private Label lblSqlPackage;
	/** Mapper包名 */
	@FXML
	private Label lblMapperPackage;
	/** 单元测试的包名 */
	@FXML
	private Label lblUnitTestPackage;

	/** 实体类类名 */
	@FXML
	private Label lblEntityName;
	/** Service类名 */
	@FXML
	private Label lblServiceName;
	/** ServiceImpl类名 */
	@FXML
	private Label lblServiceImplName;
	/** router类名 */
	@FXML
	private Label lblRouterName;
	/** SQL类名 */
	@FXML
	private Label lblSqlName;

	/** Mapper的名字 */
	@FXML
	private Label lblMapperName;
	/** 单元测试的类名 */
	@FXML
	private Label lblUnitTestName;

	/** 自定义包名与类 */
	@FXML
	private Label lblSetCustom;
	/** 自定义属性 */
	@FXML
	private Label lblSetCustomProperty;
	/** 生成文件的编码格式 */
	@FXML
	private Label lblCodeFormat;

	/** 提示文字进度条 */
	@FXML
	private Label lblRunCreateAllTips;
	/** 提示文字的默认文字 */
	private String runCreateTipsText = "正在生成";

	/** 数据树列表 */
	@FXML
	private TreeView<String> tvDataBase;
	/** 存放目录 */
	@FXML
	private TextField txtProjectPath;
	/** 数据库表名 */
	@FXML
	private TextField txtTableName;
	/** 数据库表名 */
	@FXML
	private TextField txtProjectName;
	/** 实体类包名 */
	@FXML
	private TextField txtEntityPackage;
	/** Service包名 */
	@FXML
	private TextField txtServicePackage;
	/** ServiceImpl包名 */
	@FXML
	private TextField txtServiceImplPackage;
	/** router包名 */
	@FXML
	private TextField txtRouterPackage;
	/** SQL包名 */
	@FXML
	private TextField txtSqlPackage;
	/** Mapper包名 */
	@FXML
	private TextField txtMapperPackage;
	/** 单元测试的包名 */
	@FXML
	private TextField txtUnitTestPackage;

	/** 实体类类名 */
	@FXML
	private TextField txtEntityName;
	/** Service类名 */
	@FXML
	private TextField txtServiceName;
	/** ServiceImpl类名 */
	@FXML
	private TextField txtServiceImplName;
	/** router类名 */
	@FXML
	private TextField txtRouterName;
	/** SQL类名 */
	@FXML
	private TextField txtSqlName;
	/** Mapper类名 */
	@FXML
	private TextField txtMapperName;
	/** 单元测试类名 */
	@FXML
	private TextField txtUnitTestName;

	/** 选择根目录按钮 */
	@FXML
	private Button btnSelectFile;
	/** 设置项目 */
	@FXML
	public Button btnSetProject;
	/** 执行创建 */
	@FXML
	private Button btnRunCreate;
	/** 保存配置文件 */
	@FXML
	private Button btnSaveConfig;
	/** 实体类配置按钮 */
	@FXML
	private Button btnSetEntity;
	/** 到设置按钮 */
	@FXML
	private Button btnSetService;
	/** router设置按钮 */
	@FXML
	private Button btnSetRouter;
	/** SQL设置按钮 */
	@FXML
	private Button btnSetSql;
	/** SqlAndParams的设置按钮 */
	@FXML
	private Button btnSetMapper;
	/** 自定义包名类的设置按钮 */
	@FXML
	private Button btnSetCustom;
	/** 自定义包名类属性的设置按钮 */
	@FXML
	private Button btnSetCustomProperty;
	/** 字符编码格式 */
	@FXML
	private ComboBox<String> cboCodeFormat;
	/** 生成进度条 */
	@FXML
	private ProgressBar probCreateAll;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.debug("初始化首页...");
		final int ml = 20;// 左外边距
		// 初始化图标连接与配置信息
		ImageView lblConnImage = new ImageView("image/computer.png");
		lblConnImage.setFitHeight(40);
		lblConnImage.setFitWidth(40);
		lblConnection.setGraphic(lblConnImage);
		lblConnection.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CONNECTION));
		lblConnection.setOnMouseClicked(this::onConnection);
		lblConnection.widthProperty().addListener(event -> lblConfig.setLayoutX(ml + lblConnection.getLayoutX() + lblConnection.getWidth()));

		ImageView lblConfImage = new ImageView("image/config.png");
		lblConfImage.setFitHeight(40);
		lblConfImage.setFitWidth(40);
		lblConfig.setGraphic(lblConfImage);
		lblConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CONFIG));
		lblConfig.setOnMouseClicked(this::onConfig);
		lblConfig.widthProperty().addListener(event -> lblInstructions.setLayoutX(ml + lblConfig.getLayoutX() + lblConfig.getWidth()));

		ImageView lblInstructionsImage = new ImageView("image/instructions.png");
		lblInstructionsImage.setFitHeight(40);
		lblInstructionsImage.setFitWidth(40);
		lblInstructions.setGraphic(lblInstructionsImage);
		lblInstructions.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_INSTRUCTIONS));
		lblInstructions.setOnMouseClicked(this::onInstructions);
		lblInstructions.widthProperty()
				.addListener(event -> lblSetting.setLayoutX(ml + lblInstructions.getLayoutX() + lblInstructions.getWidth()));

		ImageView lblSettingImage = new ImageView("image/setting.png");
		lblSettingImage.setFitHeight(40);
		lblSettingImage.setFitWidth(40);
		lblSetting.setGraphic(lblSettingImage);
		lblSetting.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SETTING));
		lblSetting.setOnMouseClicked(this::onSetting);

		cboCodeFormat.setEditable(true);
		cboCodeFormat.getItems().addAll("UTF-8", "GBK", "UTF-16", "UTF-32", "GB2312", "GB18030", "ISO-8859-1");
		cboCodeFormat.setValue("UTF-8");
		initLanguage();
		LOG.debug("初始化首页成功!");
		try {
			// 加载左边数据库树
			initTVDataBase();
			loadTVDataBase();
			LOG.debug("加载所有数据库到左侧树集成功!");
		} catch (Exception e1) {
			LOG.error("加载所有数据库到左侧树集失败!!!", e1);
			AlertUtil.showErrorAlert(e1.getMessage());
		}
		try {
			// 加载首页配置信息
			LOG.debug("执行查询默认配置信息并加载到首页...");
			loadIndexConfigInfo("default");// 查询使用有默认的配置,如果有就加载
			loadPlace();// 设置默认的占位符名字
			loadTemplate();// 获取模板文件夹中所有模板的名字
			LOG.debug("加载配置信息到首页成功!");
		} catch (Exception e) {
			LOG.error("加载配置信息失败!!!", e);
			AlertUtil.showErrorAlert("加载配置失败!失败原因:\r\n" + e.getMessage());
		}
	}

	// ======================方法区域================================
	/**
	 * 加载首页配置文件
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void loadIndexConfigInfo(String name) throws Exception {
		HistoryConfig config = ConfigUtil.getHistoryConfigByName(name);
		if (config == null) {
			historyConfig = new HistoryConfig();
			return;
		} else {
			historyConfig = config;
		}
		historyConfigName = config.getHistoryConfigName();
		txtProjectPath.setText(config.getProjectPath());
		txtEntityPackage.setText(config.getEntityPackage());
		if (txtEntityName.getText().contains("{c}")) {
			txtEntityName.setText(config.getEntityName());
		}
		txtServicePackage.setText(config.getServicePackage());
		if (txtServiceName.getText().contains("{c}")) {
			txtServiceName.setText(config.getServiceName());
		}
		txtServiceImplPackage.setText(config.getServiceImplPackage());
		if (txtServiceImplName.getText().contains("{c}")) {
			txtServiceImplName.setText(config.getServiceImplName());
		}
		txtRouterPackage.setText(config.getControllerPackage());
		if (txtRouterName.getText().contains("{c}")) {
			txtRouterName.setText(config.getControllerName());
		}
		txtSqlPackage.setText(config.getDaoPackage());
		if (txtSqlName.getText().contains("{c}")) {
			txtSqlName.setText(config.getDaoName());
		}
		txtMapperPackage.setText(config.getMapperPackage());
		if (txtMapperName.getText().contains("{c}")) {
			txtMapperName.setText(config.getMapperName());
		}
		txtUnitTestPackage.setText(config.getUnitTestPackage());
		if (txtUnitTestName.getText().contains("{c}")) {
			txtUnitTestName.setText(config.getUnitTestName());
		}
		cboCodeFormat.setValue(config.getCodeFormat());
	}

	/**
	 * 初始化语言
	 */
	private void initLanguage() {
		lblProjectPath.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_PROJECT_PATH));
		txtProjectPath.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_PROJECT_PATH));
		lblTableName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_TABLE_NAME));
		txtTableName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_TABLE_NAME));
		lblEntityPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ENTITY_PACKAGE));
		lblEntityName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ENTITY_NAME));
		lblServicePackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_PACKAGE));
		lblServiceName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_NAME));
		lblServiceImplPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_IMPL_PACKAGE));
		lblServiceImplName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_IMPL_NAME));
		lblRouterPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ROUTER_PACKAGE));
		lblRouterName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ROUTER_NAME));
		lblSqlPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SQL_PACKAGE));
		lblSqlName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SQL_NAME));
		lblMapperPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_MAPPER_PACKAGE));
		lblMapperName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_MAPPER_NAME));
		lblUnitTestPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_UNIT_TEST_PACKAGE));
		lblUnitTestName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_UNIT_TEST_NAME));
		lblSetCustom.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SET_CUSTOM));
		lblSetCustomProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SET_CUSTOM_PROPERTY));
		lblCodeFormat.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CODE_FORMAT));
		btnSelectFile.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SELECT_FILE));
		btnSetEntity.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetService.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetAppService.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetRouter.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetSql.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		// btnSetMapper.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetCustom.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetCustomProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnRunCreate.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_RUN_CREATE));
		btnSaveConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SAVE_CONFIG));
	}

	/**
	 * 获得当前页面的信息并实例化为配置信息对象,
	 * 
	 * @param name
	 * @return
	 */
	private HistoryConfig getThisHistoryConfig() {
		String projectPath = txtProjectPath.getText();

		String entityPackage = txtEntityPackage.getText();
		String entityName = txtEntityName.getText();

		String DOPackage = txtDOPackage.getText();
		String DOName = txtDOName.getText();

		String DTOPackage = txtDTOPackage.getText();
		String DTOName = txtDTOName.getText();

		String servicePackage = txtServicePackage.getText();
		String serviceName = txtServiceName.getText();

		String serviceImplPackage = txtServiceImplPackage.getText();
		String serviceImplName = txtServiceImplName.getText();

		String controllerPackage = txtRouterPackage.getText();
		String controllerName = txtRouterName.getText();

		String daoPackage = txtSqlPackage.getText();
		String daoName = txtSqlName.getText();

		String mapperPackage = txtMapperPackage.getText();
		String mapperName = txtMapperName.getText();

		String unitTestPackage = txtUnitTestPackage.getText();
		String unitTestName = txtUnitTestName.getText();

		String codeFormat = cboCodeFormat.getValue();

		String paramPackage = txtParamPackage.getText();
		String paramName = txtParamName.getText();

		String voPackage = txtVoPackage.getText();
		String voName = txtVoName.getText();

		String routerConvertorPackage = txtRouterConvertorPackage.getText();
		String routerConvertName = txtRouterConvertorName.getText();




		HistoryConfig config = new HistoryConfig(projectPath, entityPackage, entityName,DOPackage,DOName,DTOPackage,DTOName, servicePackage, serviceName, serviceImplPackage,
				serviceImplName, controllerPackage, controllerName, daoPackage, daoName, mapperPackage, mapperName, unitTestPackage,
				unitTestName, codeFormat,paramPackage,paramName,voPackage,voName,routerConvertorPackage,routerConvertName);

		config.setDbConfig(selectedDatabaseConfig);
		config.setEntityConfig(historyConfig.getEntityConfig());
		config.setDOConfig(historyConfig.getDOConfig());
		config.setServiceConfig(historyConfig.getServiceConfig());
		config.setServiceImplConfig(historyConfig.getServiceImplConfig());
		config.setControllerConfig(historyConfig.getControllerConfig());
		config.setDaoConfig(historyConfig.getDaoConfig());
		config.setMapperConfig(historyConfig.getMapperConfig());
		config.setUnitTestConfig(historyConfig.getUnitTestConfig());
		config.setCustomConfig(historyConfig.getCustomConfig());
		config.setCustomPropertyConfig(historyConfig.getCustomPropertyConfig());
		config.setParamConfig(historyConfig.getParamConfig());
		config.setVOConfig(historyConfig.getVOConfig());
		config.setCParamConfig(historyConfig.getCParamConfig());
		config.setCVOConfig(historyConfig.getCVOConfig());
		return config;
	}

	/**
	 * 获得当前页面的配置信息,如果某个配置信息没有初始化就实例化并初始化基本属性,
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private HistoryConfig getThisHistoryConfigAndInit(DatabaseConfig databaseConfig, String selectedTableName) {
		try {
			HistoryConfig config = getThisHistoryConfig();
			List<TableAttributeEntity> columns = DBUtil.getTableColumns(databaseConfig, selectedTableName);
			// Entity
			if (config.getEntityConfig() == null) {
				EntityConfig entityConfig = getEntityConfig(databaseConfig, selectedTableName, columns);
				config.setEntityConfig(entityConfig);
			}
			// DO
			if (config.getDOConfig() == null) {
				EntityConfig dOConfig = getEntityConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				dOConfig.setTemplateName(Constant.TEMPLATE_NAME_DO);
				config.setDOConfig(dOConfig);
			}
			// DTO
			if (config.getDTOConfig() == null) {
				EntityConfig dTOConfig = getEntityConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				dTOConfig.setTemplateName(Constant.TEMPLATE_NAME_DTO);
				config.setDTOConfig(dTOConfig);
			}
			// entity转DO
			if (config.getEntityDOConfig() == null) {
				config.setEntityDOConfig(
						Optional.ofNullable(ConfigUtil.getEntityDOTranslatorConfig(Constant.DEFAULT)).orElse(new EntityDOConfig().initDefaultValue()));
			}
			// entity转DTO
			if (config.getEntityDTOConfig() == null) {
				config.setEntityDTOConfig(
						Optional.ofNullable(ConfigUtil.getEntityDTOTranslatorConfig(Constant.DEFAULT)).orElse(new EntityDTOConfig().initDefaultValue()));
			}
			// command
			if (config.getCommandConfig() == null) {
				EntityConfig commandConfig = getEntityConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				commandConfig.setTemplateName(Constant.TEMPLATE_NAME_COMMAND);
				config.setCommandConfig(commandConfig);
			}
			// query
			if (config.getQueryConfig() == null) {
				EntityConfig queryConfig = getEntityConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				queryConfig.setTemplateName(Constant.TEMPLATE_NAME_QUERY);
				config.setQueryConfig(queryConfig);
			}

			if (config.getServiceConfig() == null) {
				config.setServiceConfig(
						Optional.ofNullable(ConfigUtil.getServiceConfig(Constant.DEFAULT)).orElse(new ServiceConfig().initDefaultValue()));
			}
			if (config.getServiceImplConfig() == null) {
				config.setServiceImplConfig(
						Optional.ofNullable(ConfigUtil.getServiceImplConfig(Constant.DEFAULT)).orElse(new ServiceImplConfig().initDefaultValue()));
			}
			if (config.getDaoConfig() == null) {
				config.setDaoConfig(Optional.ofNullable(ConfigUtil.getSQLConfig(Constant.DEFAULT)).orElse(new DaoConfig().initDefaultValue()));
			}
			if (config.getMapperConfig() == null) {
				config.setMapperConfig(
						Optional.ofNullable(ConfigUtil.getSqlAndParamsConfig(Constant.DEFAULT)).orElse(new MapperConfig().initDefaultValue()));
			}
			// AppService
			if (config.getAppServiceConfig() == null) {
				config.setAppServiceConfig(
						Optional.ofNullable(ConfigUtil.getAppServiceConfig(Constant.DEFAULT)).orElse(new AppServiceConfig().initDefaultValue()));
			}

			if (config.getCustomConfig() == null) {
				config.setCustomConfig(
						Optional.ofNullable(ConfigUtil.getCustomConfig(Constant.DEFAULT)).orElse(new CustomConfig().initDefaultValue()));
			}
			if (config.getCustomPropertyConfig() == null) {
				config.setCustomPropertyConfig(Optional.ofNullable(ConfigUtil.getCustomPropertyConfig(Constant.DEFAULT))
						.orElse(new CustomPropertyConfig().initDefaultValue()));
			}

			// admin Param
			if (config.getParamConfig() == null) {
				EntityConfig paramConfig = getParamConfig(databaseConfig, selectedTableName, columns);
				config.setParamConfig(paramConfig);
			}

			// admin VO
			if (config.getVOConfig() == null) {
				EntityConfig vOConfig = getVOConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				vOConfig.setTemplateName(Constant.TEMPLATE_NAME_VO);
				config.setVOConfig(vOConfig);
			}

			// admin controller 转换类
			if (config.getRouterConvertorConfig() == null) {
				config.setRouterConvertorConfig(
						Optional.ofNullable(ConfigUtil.getRouterConvertorConfig(Constant.DEFAULT)).orElse(new RouterConvertorConfig().initDefaultValue()));
			}
			// admin controller
			if (config.getControllerConfig() == null) {
				config.setControllerConfig(
						Optional.ofNullable(ConfigUtil.getRouterConfig(Constant.DEFAULT)).orElse(new ControllerConfig().initDefaultValue()));
			}
			// admin controllerImpl
			if (config.getControllerImplConfig() == null) {
				config.setControllerImplConfig(
						Optional.ofNullable(ConfigUtil.getRouterImplConfig(Constant.DEFAULT)).orElse(new ControllerImplConfig().initDefaultValue()));
			}
			if (config.getUnitTestConfig() == null) {
				config.setUnitTestConfig(
						Optional.ofNullable(ConfigUtil.getUnitTestConfig(Constant.DEFAULT)).orElse(new UnitTestConfig().initDefaultValue()));
			}

			// customer Param
			if (config.getCParamConfig() == null) {
				EntityConfig cParamCConfig = getCParamConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				cParamCConfig.setTemplateName(Constant.TEMPLATE_NAME_C_PARAM);
				config.setCParamConfig(cParamCConfig);
			}

			// customer VO
			if (config.getCVOConfig() == null) {
				EntityConfig cVOConfig = getCVOConfig(databaseConfig, selectedTableName, columns);
				//重新定义模板
				cVOConfig.setTemplateName(Constant.TEMPLATE_NAME_C_VO);
				config.setCVOConfig(cVOConfig);
			}

			// customer controller 转换类
			if (config.getCRouterConvertorConfig() == null) {
				config.setCRouterConvertorConfig(
						Optional.ofNullable(ConfigUtil.getCRouterConvertorConfig(Constant.DEFAULT)).orElse(new CRouterConvertorConfig().initDefaultValue()));
			}

			// customer controller
			if (config.getCControllerConfig() == null) {
				config.setCControllerConfig(
						Optional.ofNullable(ConfigUtil.getCRouterConfig(Constant.DEFAULT)).orElse(new CControllerConfig().initDefaultValue()));
			}
			// customer controllerImpl
			if (config.getCControllerImplConfig() == null) {
				config.setCControllerImplConfig(
						Optional.ofNullable(ConfigUtil.getCRouterImplConfig(Constant.DEFAULT)).orElse(new CControllerImplConfig().initDefaultValue()));
			}
			if (config.getCUnitTestConfig() == null) {
				config.setCUnitTestConfig(
						Optional.ofNullable(ConfigUtil.getCUnitTestConfig(Constant.DEFAULT)).orElse(new CUnitTestConfig().initDefaultValue()));
			}
			return config;
		} catch (Exception e) {
			LOG.debug("执行初始化配置信息-->失败:", e);
		}
		return null;
	}

	private EntityConfig getVOConfig(DatabaseConfig databaseConfig, String selectedTableName, List<TableAttributeEntity> columns) throws Exception{
		EntityConfig dOConfig = Optional.ofNullable(ConfigUtil.getVOConfig(Constant.DEFAULT)).orElse(new EntityConfig(Constant.TEMPLATE_NAME_VO));
		if (dOConfig.isFieldCamel()) {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
			}
		} else {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(attr.getTdColumnName());
			}
		}
		dOConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
		String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
		dOConfig.setPrimaryKey(primaryKey);
		return dOConfig;
	}

	private EntityConfig getParamConfig(DatabaseConfig databaseConfig, String selectedTableName, List<TableAttributeEntity> columns) throws Exception{
		EntityConfig dOConfig = Optional.ofNullable(ConfigUtil.getParamConfig(Constant.DEFAULT)).orElse(new EntityConfig(Constant.TEMPLATE_NAME_PARAM));
		if (dOConfig.isFieldCamel()) {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
			}
		} else {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(attr.getTdColumnName());
			}
		}
		dOConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
		String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
		dOConfig.setPrimaryKey(primaryKey);
		return dOConfig;
	}
	private EntityConfig getCVOConfig(DatabaseConfig databaseConfig, String selectedTableName, List<TableAttributeEntity> columns) throws Exception{
		EntityConfig dOConfig = Optional.ofNullable(ConfigUtil.getCVOConfig(Constant.DEFAULT)).orElse(new EntityConfig(Constant.TEMPLATE_NAME_C_VO));
		if (dOConfig.isFieldCamel()) {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
			}
		} else {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(attr.getTdColumnName());
			}
		}
		dOConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
		String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
		dOConfig.setPrimaryKey(primaryKey);
		return dOConfig;
	}

	private EntityConfig getCParamConfig(DatabaseConfig databaseConfig, String selectedTableName, List<TableAttributeEntity> columns) throws Exception{
		EntityConfig dOConfig = Optional.ofNullable(ConfigUtil.getCParamConfig(Constant.DEFAULT)).orElse(new EntityConfig(Constant.TEMPLATE_NAME_C_PARAM));
		if (dOConfig.isFieldCamel()) {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
			}
		} else {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(attr.getTdColumnName());
			}
		}
		dOConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
		String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
		dOConfig.setPrimaryKey(primaryKey);
		return dOConfig;
	}

	private EntityConfig getEntityConfig(DatabaseConfig databaseConfig, String selectedTableName, List<TableAttributeEntity> columns) throws Exception {
		EntityConfig dOConfig = Optional.ofNullable(ConfigUtil.getEntityConfig(Constant.DEFAULT)).orElse(new EntityConfig());
		if (dOConfig.isFieldCamel()) {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
			}
		} else {
			for (TableAttributeEntity attr : columns) {
				attr.setTdField(attr.getTdColumnName());
			}
		}
		dOConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
		String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
		dOConfig.setPrimaryKey(primaryKey);
		return dOConfig;
	}

	/**
	 * 加载默认名字
	 */
	private void loadPlace() {
		entityNamePlace = txtEntityName.getText();
		DONamePlace = txtDOName.getText();
		DTONamePlace = txtDTOName.getText();
		serviceNamePlace = txtServiceName.getText();
		serviceImplNamePlace = txtServiceImplName.getText();
		routerNamePlace = txtRouterName.getText();
		routerImplNamePlace = txtRouterImplName.getText();
		cRouterNamePlace = txtCRouterName.getText();
		cRouterImplNamePlace = txtCRouterImplName.getText();
		daoNamePlace = txtSqlName.getText();
		mapperNamePlace = txtMapperName.getText();
		unitTestPlace = txtUnitTestName.getText();
		cUnitTestPlace = txtCUnitTestName.getText();
		entityPackage = txtEntityPackage.getText();
		DOPackage = txtDOPackage.getText();
		DTOPackage = txtDTOPackage.getText();
		sqlPackage = txtSqlPackage.getText();

		mapperPackage = txtMapperPackage.getText();

		servicePackage = txtServicePackage.getText();
		serviceImplPackage = txtServiceImplPackage.getText();

		entityDTOPackage = txtEntityDTOPackage.getText();
		entityDOPackage = txtEntityDOPackage.getText();

		appServicePackage = txtAppServicePackage.getText();
		commandPackage = txtCommandPackage.getText();
		queryPackage = txtQueryPackage.getText();

		entityDTONamePlace = txtEntityDTOName.getText();
		entityDONamePlace = txtEntityDOName.getText();
		appServiceNamePlace = txtAppServiceName.getText();
		commandNamePlace = txtCommandName.getText();
		queryNamePlace = txtQueryName.getText();
		paramNamePlace = txtParamName.getText();
		voNamePlace = txtVoName.getText();
		routerConvertorNamePlace = txtRouterConvertorName.getText();
		cParamNamePlace = txtCParamName.getText();
		cVoNamePlace = txtCVoName.getText();
		cRouterConvertorNamePlace = txtCRouterConvertorName.getText();
		paramPackage = txtParamPackage.getText();
		voPackage = txtVoPackage.getText();
		routerConvertorPackage = txtRouterConvertorPackage.getText();
		routerPackage = txtRouterPackage.getText();
		routerImplPackage = txtRouterImplPackage.getText();
		unitTestPackage = txtUnitTestPackage.getText();

		cParamPackage = txtCParamPackage.getText();
		cVoPackage = txtCVoPackage.getText();
		cRouterConvertorPackage = txtCRouterConvertorPackage.getText();
		cRouterPackage = txtCRouterPackage.getText();
		cRouterImplPackage = txtCRouterImplPackage.getText();
		cUnitTestPackage = txtCUnitTestPackage.getText();



	}

	/**
	 * 右边数据库树与事件
	 */
	@SuppressWarnings("unchecked")
	public void initTVDataBase() {
		LOG.debug("加载左侧数据库树与事件....");
		tvDataBase.setShowRoot(false);
		tvDataBase.setRoot(new TreeItem<>());
		Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();
		tvDataBase.setCellFactory((TreeView<String> tv) -> {
			TreeCell<String> cell = defaultCellFactory.call(tv);
			cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				int level = tvDataBase.getTreeItemLevel(cell.getTreeItem());
				TreeCell<String> treeCell = (TreeCell<String>) event.getSource();
				TreeItem<String> treeItem = treeCell.getTreeItem();
				if (level == 1) {
					final ContextMenu contextMenu = new ContextMenu();
					MenuItem item0 = new MenuItem("打开连接");
					item0.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_OPEN_CONNECT));
					item0.setOnAction(event1 -> {
						LOG.debug("执行打开数据库连接....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(16);
									imageView.setFitWidth(16);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("打开连接失败!!!" + e);
						}
					});
					MenuItem item1 = new MenuItem("关闭连接");
					item1.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_CLOSE_CONNECT));
					item1.setOnAction(event1 -> {
						treeItem.getChildren().clear();
					});
					MenuItem item3 = new MenuItem("修改连接");
					item3.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_UPDATE_CONNECT));
					item3.setOnAction(event1 -> {
						updateOfDatabaseConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						if (updateOfDatabaseConfig != null) {
							LOG.debug("打开修改数据库连接窗口...");
							StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_UPDATE_CONNECTION);
							String title = property == null ? "修改数据库连接" : property.get();
							UpdateConnection controller = (UpdateConnection) loadFXMLPage(title, FXMLPage.UPDATE_CONNECTION, false);
							controller.setIndexController(this);
							controller.init();
							controller.showDialogStage();
						}
					});
					MenuItem item2 = new MenuItem("删除连接");
					item2.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_DELETE_CONNECT));
					item2.setOnAction(event1 -> {
						if (!AlertUtil.showConfirmAlert("确定删除该连接吗")) {
							return;
						}
						LOG.debug("执行删除数据库链接...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							ConfigUtil.deleteDatabaseConfig(selectedConfig.getConnName());
							this.loadTVDataBase();
						} catch (Exception e) {
							AlertUtil.showErrorAlert("删除数据库连接失败: " + e.getMessage());
							LOG.error("删除数据库连接失败!!!" + e);
						}
					});

					MenuItem itemCreateAll = new MenuItem("全库生成");
					itemCreateAll.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_CREATE_FULL_DB));
					itemCreateAll.setOnAction(event1 -> {
						if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
							StringProperty property = Main.LANGUAGE.get(LanguageKey.TIPS_PATH_CANT_EMPTY);
							String title = property == null ? "生成的路径不能为空" : property.get();
							AlertUtil.showWarnAlert(title);
							return;
						}
						if (!AlertUtil.showConfirmAlert("确定当前数据库里面所有的表都生成吗?")) {
							return;
						}
						LOG.debug("执行全库生成...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();

						createAllTable(selectedConfig);
					});
					contextMenu.getItems().addAll(itemCreateAll, item0, item1, item3, item2);
					cell.setContextMenu(contextMenu);
				}
				// 加载所有表
				if (event.getClickCount() == 2) {
					if (treeItem == null) {
						return;
					}
					treeItem.setExpanded(true);
					if (level == 1) {
						LOG.debug("加载所有表....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								// 获得树节点
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(18);
									imageView.setFitWidth(18);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
							LOG.debug("加载所有表成功!");
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("加载所有表失败!!!" + e);
						}
					} else if (level == 2) {
						LOG.debug("将表的数据加载到数据面板...");
						String tableName = treeCell.getTreeItem().getValue();
						selectedDatabaseConfig = (DatabaseConfig) treeItem.getParent().getGraphic().getUserData();
						selectedTableName = tableName;
						txtTableName.setText(tableName);
						String pascalTableName = StrUtil.unlineToPascal(tableName);
						txtEntityName.setText(entityNamePlace.replace("{c}", pascalTableName));
						txtDOName.setText(DONamePlace.replace("{c}", pascalTableName));
						txtDTOName.setText(DTONamePlace.replace("{c}", pascalTableName));
						txtServiceName.setText(serviceNamePlace.replace("{c}", pascalTableName));
						txtServiceImplName.setText(serviceImplNamePlace.replace("{c}", pascalTableName));
						txtSqlName.setText(daoNamePlace.replace("{c}", pascalTableName));
						txtMapperName.setText(mapperNamePlace.replace("{c}", pascalTableName));
						txtUnitTestName.setText(unitTestPlace.replace("{c}", pascalTableName));
						txtEntityDTOName.setText(entityDTONamePlace.replace("{c}", pascalTableName+"EntityDTO"));
						txtEntityDOName.setText(entityDONamePlace.replace("{c}", pascalTableName+"EntityDO"));
						txtAppServiceName.setText(appServiceNamePlace.replace("{c}", pascalTableName));
						txtCommandName.setText(commandNamePlace.replace("{c}", pascalTableName));
						txtQueryName.setText(queryNamePlace.replace("{c}", pascalTableName));
						txtParamName.setText(paramNamePlace.replace("{c}", pascalTableName));
						txtVoName.setText(voNamePlace.replace("{c}", pascalTableName));
						txtRouterConvertorName.setText(routerConvertorNamePlace.replace("{c}", pascalTableName));
						txtRouterName.setText(routerNamePlace.replace("{c}", pascalTableName));
						txtRouterImplName.setText(routerImplNamePlace.replace("{c}", pascalTableName));
						txtCParamName.setText(cParamNamePlace.replace("{c}", pascalTableName));
						txtCVoName.setText(cVoNamePlace.replace("{c}", pascalTableName));
						txtCRouterConvertorName.setText(cRouterConvertorNamePlace.replace("{c}", pascalTableName));
						txtCRouterName.setText(cRouterNamePlace.replace("{c}", pascalTableName));
						txtCRouterImplName.setText(cRouterImplNamePlace.replace("{c}", pascalTableName));
						txtCUnitTestName.setText(cUnitTestPlace.replace("{c}", pascalTableName));
						LOG.debug("将表的数据加载到数据面板成功!");
					}
				}
			});
			return cell;
		});
	}

	/**
	 * 加载数据库到树集
	 * 
	 * @throws Exception
	 */
	public void loadTVDataBase() throws Exception {
		TreeItem<String> rootTreeItem = tvDataBase.getRoot();
		rootTreeItem.getChildren().clear();
		List<DatabaseConfig> item = null;
		item = ConfigUtil.getDatabaseConfig();
		for (DatabaseConfig dbConfig : item) {
			TreeItem<String> treeItem = new TreeItem<String>();
			treeItem.setValue(dbConfig.getConnName());
			ImageView dbImage = new ImageView("image/database.png");
			dbImage.setFitHeight(20);
			dbImage.setFitWidth(20);
			dbImage.setUserData(dbConfig);
			treeItem.setGraphic(dbImage);
			rootTreeItem.getChildren().add(treeItem);
		}
	}

	/**
	 * 加载模板文件夹里面所有模板的名字
	 * 
	 * @throws IOException
	 */
	public void loadTemplate() {
		LOG.debug("执行加载模板文件夹里面所有模板的名字...");
		try {
			this.templateNameItems = Arrays.stream(new File(FileUtil.getFilePath()).list()).filter(s -> s.endsWith(".ftl")).collect(Collectors.toList());
			if (this.templateNameItems == null) {
				this.templateNameItems = new ArrayList<>();
			}
			LOG.debug("执行加载模板文件夹里面所有模板的名字-->成功!");
		} catch (Exception e) {
			LOG.error("执行加载模板文件夹里面所有模板的名字-->失败:", e);
			AlertUtil.showErrorAlert(e.toString());
		}
	}

	/**
	 * 获得模板需要的上下文
	 * 
	 * @param databaseConfig
	 *          数据库配置文件
	 * @param tableName
	 *          表的名字,如果表名不为空,将类名设置为默认值占位表名,如果直接使用版面数据输入null
	 * @return
	 * @throws Exception
	 */
	public GeneratorContent getGeneratorContent(DatabaseConfig databaseConfig, String tableName) throws Exception {
		GeneratorContent content = new GeneratorContent();
		HistoryConfig history = getThisHistoryConfigAndInit(databaseConfig, tableName != null ? tableName : selectedTableName);
		// 数据库属性
		DatabaseContent databaseContent = new DatabaseContent();
		ConverterUtil.databaseConfigToContent(databaseConfig, databaseContent);
		content.setDatabase(databaseContent);

		// 数据库表属性
		TableContent tableContent = DBUtil.getTableAttribute(databaseConfig, tableName);
		content.setTable(tableContent);
		// 实体类属性
		EntityConfig ec = history.getEntityConfig();

		String className = tableName != null ? entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtEntityName.getText();
		String entityName = tableName != null ? entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtEntityName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent entityContent = new EntityContent(txtEntityPackage.getText(), entityName, tableName);
		ConverterUtil.entityConfigToContent(ec, entityContent);
		content.setEntity(entityContent);

		// DO类属性
		String DOName = tableName != null ? DONamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtDOName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent DOContent = new EntityContent(txtDOPackage.getText(), DOName, tableName);
		ConverterUtil.entityConfigToContent(ec, DOContent);
		content.setDO(DOContent);

		// DTO类属性
		String DTOName = tableName != null ? DTONamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtDTOName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent DTOContent = new EntityContent(txtDTOPackage.getText(), DTOName, tableName);
		ConverterUtil.entityConfigToContent(ec, DTOContent);
		content.setDTO(DTOContent);

		// entity/DO互转属性
		EntityDOConfig edc = history.getEntityDOConfig();
		String entityDOName = txtEntityDOName.getText().contains("{c}")
				? txtEntityDOName.getText().replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtEntityDOName.getText();
		EntityDOContent entityDOContent = new EntityDOContent(txtEntityDOPackage.getText(), entityDOName);
		ConverterUtil.entityDOConfigToContent(edc, entityDOContent, className);
		content.setEntityDO(entityDOContent);

		// entity/DTO互转属性
		EntityDTOConfig edt = history.getEntityDTOConfig();
		String entityDTOName = txtEntityDTOName.getText().contains("{c}")
				? txtEntityDTOName.getText().replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtEntityDTOName.getText();
		EntityDTOContent entityDTOContent = new EntityDTOContent(txtEntityDTOPackage.getText(), entityDTOName);
		ConverterUtil.entityDTOConfigToContent(edt, entityDOContent, className);
		content.setEntityDTO(entityDTOContent);

		// command
		String commandName = tableName != null ? commandNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtCommandName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent commandContent = new EntityContent(txtCommandPackage.getText(), commandName, tableName);
		ConverterUtil.entityConfigToContent(ec, commandContent);
		content.setCommand(commandContent);

		// query
		String queryName = tableName != null ? queryNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtQueryName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent queryContent = new EntityContent(txtQueryPackage.getText(), queryName, tableName);
		ConverterUtil.entityConfigToContent(ec, queryContent);
		content.setQuery(queryContent);

		// Service属性
		ServiceConfig sc = history.getServiceConfig();
		String serviceName = txtServiceName.getText().contains("{c}")
				? serviceNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtServiceName.getText();
		ServiceContent serviceContent = new ServiceContent(txtServicePackage.getText(), serviceName);
		ConverterUtil.serviceConfigToContent(sc, serviceContent, className);
		content.setService(serviceContent);
		// ServiceImpl属性
		ServiceImplConfig sci = history.getServiceImplConfig();
		String serviceNameImplName = txtServiceImplName.getText().contains("{c}")
				? serviceImplNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtServiceImplName.getText();
		ServiceImplContent serviceImplContent = new ServiceImplContent(txtServiceImplPackage.getText(), serviceNameImplName);
		ConverterUtil.serviceImplConfigToContent(sci, serviceImplContent, className);
		content.setServiceImpl(serviceImplContent);
		//AppService属性
		AppServiceConfig asc = history.getAppServiceConfig();
		String appServiceName = txtAppServiceName.getText().contains("{c}")
				? appServiceNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtAppServiceName.getText();
		AppServiceContent appServiceContent = new AppServiceContent(txtAppServicePackage.getText(), appServiceName);
		ConverterUtil.appServiceConfigToContent(asc, appServiceContent, className);
		content.setAppService(appServiceContent);
		// dao属性
		String sqlName = txtSqlName.getText().contains("{c}")
				? daoNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtSqlName.getText();
		DaoConfig sql = history.getDaoConfig();
		DaoContent sqlContent = new DaoContent(txtSqlPackage.getText(), sqlName);
		ConverterUtil.SqlConfigToContent(sql, sqlContent, className);
		content.setDao(sqlContent);

		// Mapper属性
		MapperConfig mapperConfig = history.getMapperConfig();
		String mapperName = txtMapperName.getText().contains("{c}")
				? mapperNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtMapperName.getText();
		MapperContent mapperContent = new MapperContent(txtMapperPackage.getText(), mapperName);
		ConverterUtil.mapperConfigToContent(mapperConfig, mapperContent, className);
		content.setMapper(mapperContent);
		// 自定义包类属性
		CustomConfig customConfig = history.getCustomConfig();
		CustomContent customContent = new CustomContent();
		ConverterUtil.customConfigToContent(customConfig, customContent, className);
		content.setCustom(customContent);
		// 自定义属性
		CustomPropertyConfig propertyConfig = history.getCustomPropertyConfig();
		CustomPropertyContent propertyContent = new CustomPropertyContent();
		ConverterUtil.customPropertyConfigToContent(propertyConfig, propertyContent, className);
		content.setCustomProperty(propertyContent);

		// b端 param属性
		EntityConfig paramConfig = history.getParamConfig();
		String paramName = tableName != null ? paramNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtParamName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent paramContent = new EntityContent(txtParamPackage.getText(), paramName, tableName);
		ConverterUtil.entityConfigToContent(paramConfig, paramContent);
		content.setParam(paramContent);


		// b端 vo类属性
		EntityConfig voConfig = history.getVOConfig();
		String voName = tableName != null ? voNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtVoName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent voContent = new EntityContent(txtVoPackage.getText(), voName, tableName);
		ConverterUtil.entityConfigToContent(voConfig, voContent);
		content.setVO(voContent);

		// b端controller 互转属性
		RouterConvertorConfig rcc = history.getRouterConvertorConfig();
		String routerConvertorName = txtRouterConvertorName.getText().contains("{c}")
				? txtRouterConvertorName.getText().replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtRouterConvertorName.getText();
		RouterConvertorContent routerConvertorContent = new RouterConvertorContent(txtRouterConvertorPackage.getText(), routerConvertorName);
		ConverterUtil.routerConvertorConfigToContent(rcc, routerConvertorContent, className);
		content.setRouterConvertor(routerConvertorContent);
		// b端 Controller属性
		String routerName = txtRouterName.getText().contains("{c}")
				? routerNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtRouterName.getText();
		ControllerConfig router = history.getControllerConfig();
		ControllerContent routerContent = new ControllerContent(txtRouterPackage.getText(), routerName);
		ConverterUtil.routerConfigToContent(router, routerContent, className);
		content.setController(routerContent);

		// b端 ControllerImpl属性
		String routerImplName = txtRouterImplName.getText().contains("{c}")
				? routerImplNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtRouterImplName.getText();
		ControllerImplConfig routerImpl = history.getControllerImplConfig();
		ControllerImplContent routerImplContent = new ControllerImplContent(txtRouterImplPackage.getText(), routerImplName);
		ConverterUtil.routerImplConfigToContent(routerImpl, routerImplContent, className);
		content.setControllerImpl(routerImplContent);

		// b端单元测试 属性
		String testName = txtUnitTestName.getText().contains("{c}")
				? unitTestPlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtUnitTestName.getText();
		UnitTestConfig unit = history.getUnitTestConfig();
		UnitTestContent unitTestContent = new UnitTestContent(txtUnitTestPackage.getText(), testName);
		ConverterUtil.unitTestConfigToContent(unit, unitTestContent, className);
		content.setUnitTest(unitTestContent);

		// c端 param属性
		EntityConfig cParamConfig = history.getCParamConfig();
		String cParamName = tableName != null ? cParamNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtCParamName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent cParamContent = new EntityContent(txtCParamPackage.getText(), cParamName, tableName);
		ConverterUtil.entityConfigToContent(cParamConfig, cParamContent);
		content.setCParam(cParamContent);


		// c端 vo类属性
		EntityConfig cvoConfig = history.getCVOConfig();
		String cVoName = tableName != null ? cVoNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtCVoName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent cVoContent = new EntityContent(txtCVoPackage.getText(), cVoName, tableName);
		ConverterUtil.entityConfigToContent(cvoConfig, cVoContent);
		content.setCVO(cVoContent);

		// c端controller 互转属性
		CRouterConvertorConfig crc = history.getCRouterConvertorConfig();
		String cRouterConvertorName = txtCRouterConvertorName.getText().contains("{c}")
				? txtCRouterConvertorName.getText().replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtCRouterConvertorName.getText();
		CRouterConvertorContent cRouterConvertorContent = new CRouterConvertorContent(txtCRouterConvertorPackage.getText(), cRouterConvertorName);
		ConverterUtil.cRouterConvertorConfigToContent(crc, cRouterConvertorContent, className);
		content.setCRouterConvertor(cRouterConvertorContent);
		// c端 Controller属性
		String CRouterName = txtCRouterName.getText().contains("{c}")
				? cRouterNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtCRouterName.getText();
		CControllerConfig cRouter = history.getCControllerConfig();
		CControllerContent cRouterContent = new CControllerContent(txtCRouterPackage.getText(), CRouterName);
		ConverterUtil.cRouterConfigToContent(cRouter, cRouterContent, className);
		content.setCController(cRouterContent);

		// c端 ControllerImpl属性
		String cRouterImplName = txtCRouterImplName.getText().contains("{c}")
				? cRouterImplNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtCRouterImplName.getText();
		CControllerImplConfig cRouterImpl = history.getCControllerImplConfig();
		CControllerImplContent cRouterImplContent = new CControllerImplContent(txtCRouterImplPackage.getText(), cRouterImplName);
		ConverterUtil.cRouterImplConfigToContent(cRouterImpl, cRouterImplContent, className);
		content.setCControllerImpl(cRouterImplContent);

		// c端 单元测试 属性
		String cTestName = txtCUnitTestName.getText().contains("{c}")
				? cUnitTestPlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtCUnitTestName.getText();
		CUnitTestConfig cUnit = history.getCUnitTestConfig();
		CUnitTestContent cUnitTestContent = new CUnitTestContent(txtCUnitTestPackage.getText(), cTestName);
		ConverterUtil.cUnitTestConfigToContent(cUnit, cUnitTestContent, className);
		content.setCUnitTest(cUnitTestContent);
		return content;
	}
	/**
	 * 将数据库中所有的表创建
	 * 
	 * @param databaseConfig
	 */
	public void createAllTable(DatabaseConfig databaseConfig) {
		try {
			List<String> tables = DBUtil.getTableNames(databaseConfig);
			if (tables.size() == 0) {
				AlertUtil.showWarnAlert("当前数据库不存在表");
				return;
			}
			double progIndex = 1.0 / tables.size();
			probCreateAll.setVisible(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					for (int i = 0; i < tables.size(); i++) {
						updateProgress(progIndex * (i + 1), 1.0);
						updateMessage(runCreateTipsText + " {t} ...".replace("{t}", tables.get(i)));
						createAllRun(databaseConfig, tables.get(i));
						loadIndexConfigInfo(historyConfigName == null ? "default" : historyConfigName);
					}
					updateMessage("创建成功!");
					LOG.debug("执行全库生成-->成功");
					return null;
				}
			};
			probCreateAll.progressProperty().bind(task.progressProperty());
			lblRunCreateAllTips.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建文件-->失败:" + e);
		}
	}
	/**
	 * 全库生成的执行方法
	 * 
	 * @param databaseConfig
	 *          数据库连接信息
	 * @param tableName
	 *          表的名字
	 * @throws Exception
	 */
	public void createAllRun(DatabaseConfig databaseConfig, String tableName) throws Exception {
		HistoryConfig historyConfig = getThisHistoryConfigAndInit(databaseConfig, tableName);
		GeneratorContent content = getGeneratorContent(databaseConfig, tableName);
		// 项目生成的路径
		String projectPath = txtProjectPath.getText();
		String codeFormat = cboCodeFormat.getValue();
		// 实体类的名字
		String entityName = StrUtil.unlineToPascal(tableName);
		// 生成实体类
		try {
			EntityConfig config = historyConfig.getEntityConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtEntityPackage.getText(),
						entityNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成实体类-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成实体类-->失败:", e);
		}
		// 生成Service
		try {
			ServiceConfig config = historyConfig.getServiceConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServicePackage.getText(),
						serviceNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成Service-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成Service-->失败:", e);
		}
		// 生成ServiceImpl
		try {
			ServiceImplConfig config = historyConfig.getServiceImplConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServiceImplPackage.getText(),
						serviceImplNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成ServiceImpl-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成ServiceImpl-->失败:", e);
		}
		// 生成dao
		try {
			DaoConfig config = historyConfig.getDaoConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtSqlPackage.getText(),
						daoNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成DAO-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成DAO-->失败:", e);
		}
		// 生成Mapper
		try {
			MapperConfig config = historyConfig.getMapperConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
					String templateName = config.getTemplateName();
					if (templateName.equals(Constant.TEMPLATE_NAME_MAPPER)) {
						templateName = databaseConfig.getDbType() + Constant.TEMPLATE_NAME_MAPPER_SUFFIX;
					}
					CreateFileUtil.createFile(content, templateName, projectPath, txtMapperPackage.getText(),
							mapperNamePlace.replace("{c}", entityName), codeFormat, config.isOverrideFile());
				}
			}
			LOG.debug("执行生成Mapper-->成功!");
		} catch (Exception e) {
			LOG.error("执行生成Mapper-->失败:", e);
		}
		// 生成Router
		try {
			ControllerConfig config = historyConfig.getControllerConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtRouterPackage.getText(),
						routerNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成Router-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成Router-->失败:", e);
		}
		// 生成单元测试
		try {
			UnitTestConfig config = historyConfig.getUnitTestConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtUnitTestPackage.getText(),
						unitTestPlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成单元测试-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成单元测试-->失败:", e);
		}

		CustomConfig config = historyConfig.getCustomConfig();
		if (config.getTableItem() != null) {
			for (TableAttributeKeyValueTemplate custom : config.getTableItem()) {
				if (!StrUtil.isNullOrEmpty(custom.getTemplateValue())) {
					try {
						String loCase = StrUtil.fristToLoCase(entityName);
						String cpackage = custom.getPackageName().replace("{C}", entityName).replace("{c}", loCase);
						String name = custom.getClassName().replace("{C}", entityName).replace("{c}", loCase);
						CreateFileUtil.createFile(content, custom.getTemplateValue(), projectPath, cpackage, name + custom.getSuffix(), codeFormat,
								config.isOverrideFile());
					} catch (Exception e) {
						LOG.error("执行生成自定义生成包类-->失败:", e);
					}
				}
			}
		}

	}

	// ============================事件区域=================================
	/**
	 * 执行创建
	 * 
	 * @param event
	 */
	public void onCreate(ActionEvent event) {
		LOG.debug("执行创建...");
		try {
			if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
				StringProperty property = Main.LANGUAGE.get(LanguageKey.TIPS_PATH_CANT_EMPTY);
				String tips = property == null ? "生成的路径不能为空" : property.get();
				AlertUtil.showWarnAlert(tips);
				return;
			}
			if (StrUtil.isNullOrEmpty(txtTableName.getText())) {
				StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_CREATE_TABLE);
				String tips = property == null ? "请双击左侧数据选择想要生成的表,或者在左侧右键全库生成!" : property.get();
				AlertUtil.showWarnAlert(tips);
				return;
			}
			probCreateAll.setVisible(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					updateProgress(1, ALL_FILE_NUM);
					// 项目生成的路径
					String projectPath = txtProjectPath.getText();
					String codeFormat = cboCodeFormat.getValue();
					HistoryConfig historyConfig = getThisHistoryConfigAndInit(selectedDatabaseConfig, txtTableName.getText());
					GeneratorContent content = getGeneratorContent(selectedDatabaseConfig, null);
					String controllerPath = projectPath + "/" + PathConfig.CONTROLLER;
					String controllerTestPath = projectPath + "/" + PathConfig.CONTROLLER_TEST;
					String applicationPath = projectPath + "/" + PathConfig.APPLICATION;
					String domainPath = projectPath + "/" + PathConfig.DOMAIN;
					String infrastructurePath = projectPath + "/" + PathConfig.INFRASTRUCTURE;

					updateProgress(2, ALL_FILE_NUM);
					// 生成实体类
					try {
						EntityConfig config = historyConfig.getEntityConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), domainPath, txtEntityPackage.getText(),
									txtEntityName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成ENTITY类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成ENTITY类:" + txtEntityName.getText() + "失败:" + e);
						LOG.error("执行生成ENTITY类-->失败:", e);
					}
					updateProgress(3, ALL_FILE_NUM);
					// 生成DO类
					try {
						EntityConfig config = historyConfig.getDOConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtDOName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), infrastructurePath, txtDOPackage.getText(),
									txtDOName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成DO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成DO类:" + txtDOName.getText() + "失败:" + e);
						LOG.error("执行生成DO类-->失败:", e);
					}
					updateProgress(4, ALL_FILE_NUM);
					// 生成DTO类
					try {
						EntityConfig config = historyConfig.getDTOConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtDTOName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), applicationPath, txtDTOPackage.getText(),
									txtDTOName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成DTO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成DTO类:" + txtDTOName.getText() + "失败:" + e);
						LOG.error("执行生成DTO类-->失败:", e);
					}
					updateProgress(5, ALL_FILE_NUM);
					// 生成Command类
					try {
						EntityConfig config = historyConfig.getCommandConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCommandName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), applicationPath, txtCommandPackage.getText(),
									txtCommandName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成Command类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Command类:" + txtCommandName.getText() + "失败:" + e);
						LOG.error("执行生成Command类-->失败:", e);
					}
					updateProgress(6, ALL_FILE_NUM);
					// 生成Query类
					try {
						EntityConfig config = historyConfig.getQueryConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtQueryName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), applicationPath, txtQueryPackage.getText(),
									txtQueryName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成Query类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Query类:" + txtQueryName.getText() + "失败:" + e);
						LOG.error("执行生成Query类-->失败:", e);
					}
					updateProgress(7, ALL_FILE_NUM);
					// 生成entity/DO类
					try {
						EntityDOConfig config = historyConfig.getEntityDOConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityDOName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), infrastructurePath, txtEntityDOPackage.getText(),
									txtEntityDOName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成entity/DO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成entity/DO类:" + txtEntityDOName.getText() + "失败:" + e);
						LOG.error("执行生成entity/DO类-->失败:", e);
					}
					updateProgress(8, ALL_FILE_NUM);
					// 生成entity/DTO类
					try {
						EntityDTOConfig config = historyConfig.getEntityDTOConfig();
						String path = projectPath + "/" + PathConfig.APPLICATION;
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityDTOName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), applicationPath, txtEntityDTOPackage.getText(),
									txtEntityDTOName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成entity/DTO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成entity/DTO类:" + txtEntityDTOName.getText() + "失败:" + e);
						LOG.error("执行生成entity/DTO类-->失败:", e);
					}
					// 生成Repository
					updateProgress(9, ALL_FILE_NUM);
					try {
						ServiceConfig config = historyConfig.getServiceConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), domainPath, txtServicePackage.getText(),
									txtServiceName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成Service-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Service:" + txtServiceName.getText() + "失败:" + e);
						LOG.error("执行生成Service-->失败:", e);
					}
					// 生成ServiceImpl
					updateProgress(10, ALL_FILE_NUM);
					try {
						ServiceImplConfig config = historyConfig.getServiceImplConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceImplName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), infrastructurePath, txtServiceImplPackage.getText(),
									txtServiceImplName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成ServiceImpl-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成ServiceImpl:" + txtServiceImplName.getText() + "失败:" + e);
						LOG.error("执行生成ServiceImpl-->失败:", e);
					}
					updateProgress(11, ALL_FILE_NUM);
					// 生成AppService
					try {
						AppServiceConfig config = historyConfig.getAppServiceConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtAppServiceName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), applicationPath, txtAppServicePackage.getText(),
									txtAppServiceName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成AppService-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成AppService:" + txtAppServiceName.getText() + "失败:" + e);
						LOG.error("执行生成AppService-->失败:", e);
					}
					updateProgress(12, ALL_FILE_NUM);
					// 生成SQL
					try {
						DaoConfig config = historyConfig.getDaoConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtSqlName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), infrastructurePath, txtSqlPackage.getText(),
									txtSqlName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成DAO-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成DAO:" + txtSqlName.getText() + "失败:" + e);
						LOG.error("执行生成DAO-->失败:", e);
					}

					// 生成Mapper
					updateProgress(13, ALL_FILE_NUM);
					try {
						MapperConfig config = historyConfig.getMapperConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtMapperPackage.getText() + ""));
							String templateName = config.getTemplateName();
							if (templateName.equals(Constant.TEMPLATE_NAME_MAPPER)) {
								templateName = selectedDatabaseConfig.getDbType() + Constant.TEMPLATE_NAME_MAPPER_SUFFIX;
							}
							CreateFileUtil.createFile(content, templateName, infrastructurePath, txtMapperPackage.getText(), txtMapperName.getText(), codeFormat,
									config.isOverrideFile());
						}
						LOG.debug("执行生成Mapper-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Mapper:" + txtMapperName.getText() + "失败:" + e);
						LOG.error("执行生成Mapper-->失败:", e);
					}

					updateProgress(14, ALL_FILE_NUM);
					// 生成b param 类
					try {
						EntityConfig config = historyConfig.getParamConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtParamName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtParamPackage.getText(),
									txtParamName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端param类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端param类:" + txtParamName.getText() + "失败:" + e);
						LOG.error("执行生成b端param类-->失败:", e);
					}
					updateProgress(15, ALL_FILE_NUM);
					// 生成b vo类
					try {
						EntityConfig config = historyConfig.getVOConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtVoName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtVoPackage.getText(),
									txtVoName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端VO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端VO类:" + txtVoName.getText() + "失败:" + e);
						LOG.error("执行生成b端VO类-->失败:", e);
					}
					updateProgress(16, ALL_FILE_NUM);
					// 生成b convertor类
					try {
						RouterConvertorConfig config = historyConfig.getRouterConvertorConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtRouterConvertorName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtRouterConvertorPackage.getText(),
									txtRouterConvertorName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端RouterConvertor类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端RouterConvertor类:" + txtRouterConvertorName.getText() + "失败:" + e);
						LOG.error("执行生成b端RouterConvertor类-->失败:", e);
					}

					updateProgress(17, ALL_FILE_NUM);
					// 生成b Controller
					try {
						ControllerConfig config = historyConfig.getControllerConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtRouterName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtRouterPackage.getText(),
									txtRouterName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端Controller-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端Controller:" + txtRouterName.getText() + "失败:" + e);
						LOG.error("执行生成b端Controller-->失败:", e);
					}
					updateProgress(18, ALL_FILE_NUM);
					// 生成b ControllerImpl
					try {
						ControllerImplConfig config = historyConfig.getControllerImplConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtRouterImplName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtRouterImplPackage.getText(),
									txtRouterImplName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端ControllerImpl-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端ControllerImpl:" + txtRouterImplName.getText() + "失败:" + e);
						LOG.error("执行生成b端ControllerImpl-->失败:", e);
					}
					// 生成b单元测试
					updateProgress(19, ALL_FILE_NUM);
					try {
						UnitTestConfig config = historyConfig.getUnitTestConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtUnitTestName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerTestPath, txtUnitTestPackage.getText(),
									txtUnitTestName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成b端单元测试-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成b端单元测试:" + txtUnitTestName.getText() + "失败:" + e);
						LOG.error("执行生成b端单元测试-->失败:", e);
					}
					updateProgress(20, ALL_FILE_NUM);
					// 生成c param 类
					try {
						EntityConfig config = historyConfig.getCParamConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCParamName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtCParamPackage.getText(),
									txtCParamName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端param类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端param类:" + txtCParamName.getText() + "失败:" + e);
						LOG.error("执行生成c端param类-->失败:", e);
					}
					updateProgress(21, ALL_FILE_NUM);
					// 生成c vo类
					try {
						EntityConfig config = historyConfig.getCVOConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCVoName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtCVoPackage.getText(),
									txtCVoName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端VO类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端VO类:" + txtCVoName.getText() + "失败:" + e);
						LOG.error("执行生成c端VO类-->失败:", e);
					}
					updateProgress(22, ALL_FILE_NUM);
					// 生成c convertor类
					try {
						CRouterConvertorConfig config = historyConfig.getCRouterConvertorConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCRouterConvertorName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtCRouterConvertorPackage.getText(),
									txtCRouterConvertorName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端RouterConvertor类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端RouterConvertor类:" + txtCRouterConvertorName.getText() + "失败:" + e);
						LOG.error("执行生成c端RouterConvertor类-->失败:", e);
					}

					updateProgress(23, ALL_FILE_NUM);
					// 生成c Controller
					try {
						CControllerConfig config = historyConfig.getCControllerConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCRouterName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtCRouterPackage.getText(),
									txtCRouterName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端Controller-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端Controller:" + txtCRouterName.getText() + "失败:" + e);
						LOG.error("执行生成c端Controller-->失败:", e);
					}
					updateProgress(24, ALL_FILE_NUM);
					// 生成c ControllerImpl
					try {
						CControllerImplConfig config = historyConfig.getCControllerImplConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCRouterImplName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerPath, txtCRouterImplPackage.getText(),
									txtCRouterImplName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端ControllerImpl-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端ControllerImpl:" + txtCRouterImplName.getText() + "失败:" + e);
						LOG.error("执行生成c端ControllerImpl-->失败:", e);
					}
					// 生成c单元测试
					updateProgress(25, ALL_FILE_NUM);
					try {
						CUnitTestConfig config = historyConfig.getCUnitTestConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtCUnitTestName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), controllerTestPath, txtCUnitTestPackage.getText(),
									txtCUnitTestName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成c端单元测试-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成c端单元测试:" + txtCUnitTestName.getText() + "失败:" + e);
						LOG.error("执行生成c端单元测试-->失败:", e);
					}

					CustomConfig config = historyConfig.getCustomConfig();
					if (config.getTableItem() != null) {
						for (TableAttributeKeyValueTemplate custom : config.getTableItem()) {
							if (!StrUtil.isNullOrEmpty(custom.getTemplateValue())) {
								try {
									String loCase = StrUtil.fristToLoCase(txtEntityName.getText());
									String cpackage = custom.getPackageName().replace("{C}", txtEntityName.getText()).replace("{c}", loCase);
									String name = custom.getClassName().replace("{C}", txtEntityName.getText()).replace("{c}", loCase);
									updateMessage(runCreateTipsText + " {t} ...".replace("{t}", custom.getKey() + ""));
									CreateFileUtil.createFile(content, custom.getTemplateValue(), projectPath, cpackage, name + custom.getSuffix(),
											codeFormat, config.isOverrideFile());
								} catch (Exception e) {
									updateMessage("执行生成自定义生成包类:" + custom.getKey() + "失败:" + e);
									LOG.error("执行生成自定义生成包类-->失败:", e);
								}
							}
						}
					}
					updateProgress(26, ALL_FILE_NUM);

					loadIndexConfigInfo(historyConfigName == null ? "default" : historyConfigName);
					updateMessage("创建成功!");
					LOG.debug("执行创建-->成功!");
					return null;
				}
			};
			probCreateAll.progressProperty().bind(task.progressProperty());
			lblRunCreateAllTips.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建-->失败:", e);
		}
	}

	/**
	 * 保存配置文件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		LOG.debug("执行保存配置文件...");
		// 检查是否类名是否存在占位符
		boolean indexOf = StrUtil.indexOf("{c}", txtEntityName.getText(), txtServiceName.getText(), txtServiceImplName.getText(),
				txtRouterName.getText(), txtSqlName.getText(), txtUnitTestName.getText());
		if (!indexOf) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_NOT_C_TIPS);
			String title = property == null ? "所有类名里面必须包含用于替换表名的占位符: {c}" : property.get();
			AlertUtil.showWarnAlert(title);
			return;
		}
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("保存当前配置");
		StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_TIPS);
		String title = property == null ? "请输入配置名称:\\r\\n(表名不在保存范围内必须通过数据库加载!!!)" : property.get();
		dialog.setContentText(title);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String name = result.map(n -> n).orElse("null");
			try {
				HistoryConfig config = getThisHistoryConfig();
				config.setHistoryConfigName(name);
				ConfigUtil.saveHistoryConfig(config);
				AlertUtil.showInfoAlert("保存配置成功!");
				LOG.debug("保存配置成功!");
			} catch (Exception e) {
				AlertUtil.showErrorAlert("保存配置失败!失败原因:\r\n" + e.getMessage());
				LOG.error("保存配置失败!!!", e);
			}
		}
	}

	/**
	 * 数据库连接
	 * 
	 * @param event
	 */
	public void onConnection(MouseEvent event) {
		StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_CREATE_CONNECTION);
		String title = property == null ? "新建数据库连接" : property.get();
		ConnectionController controller = (ConnectionController) loadFXMLPage(title, FXMLPage.CONNECTION, false);
		controller.setIndexController(this);
		controller.showDialogStage();
	}

	/**
	 * 配置信息
	 * 
	 * @param event
	 */
	public void onConfig(MouseEvent event) {
		HistoryConfigController controller = (HistoryConfigController) loadFXMLPage("配置信息管理", FXMLPage.HISTORY_CONFIG, false);
		controller.setIndexController(this);
		controller.showDialogStage();

	}

	/**
	 * 使用说明
	 * 
	 * @param event
	 */
	public void onInstructions(MouseEvent event) {
		AboutController controller = (AboutController) loadFXMLPage("使用说明", FXMLPage.ABOUT, false, false);
		controller.showDialogStage();
	}

	/**
	 * 打开设置的事件
	 * 
	 * @param event
	 */
	public void onSetting(MouseEvent event) {
		SettingController controller = (SettingController) loadFXMLPage("设置", FXMLPage.SETTING, false, false);
		controller.showDialogStage();
	}

	/**
	 * 选择项目文件
	 * 
	 * @param event
	 */
	public void onSelectProjectPath(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(super.getPrimaryStage());
		if (file != null) {
			txtProjectPath.setText(file.getPath());
			LOG.debug("选择文件项目目录:" + file.getPath());
		}
	}

	/**
	 * 打开设置实体类
	 * 
	 * @param event
	 */
	public void onSetEntity(ActionEvent event) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetEntityAttributeController controller = (SetEntityAttributeController) loadFXMLPage("Entity Attribute Setting",
				FXMLPage.SET_ENTITY_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Service
	 * 
	 * @param event
	 */
	public void onSetService(ActionEvent event) {
		SetServiceController controller = (SetServiceController) loadFXMLPage("Repository Setting", FXMLPage.SET_ROUTER_SERVICE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Service
	 * 
	 * @param event
	 */
	public void onSetServiceImpl(ActionEvent event) {
		SetServiceImplController controller = (SetServiceImplController) loadFXMLPage("Service implement Setting",
				FXMLPage.SET_ROUTER_SERVICE_IMPL, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Router
	 * 
	 * @param event
	 */
	public void onSetRouter(ActionEvent event) {
		SetRouterController controller = (SetRouterController) loadFXMLPage("c Controller Setting", FXMLPage.SET_ROUTER, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SetSQL
	 * 
	 * @param event
	 */
	public void onSetSQL(ActionEvent event) {
		SetSqlController controller = (SetSqlController) loadFXMLPage("DAO Setting", FXMLPage.SET_SQL, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}


	/**
	 * 打开设置SqlAndParams
	 * 
	 * @param event
	 */
	public void onSetMapper(ActionEvent event) {
		SetMapperController controller = (SetMapperController) loadFXMLPage("Mapper Setting", FXMLPage.SET_MAPPER, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置AppService
	 *
	 * @param event
	 */
	public void onSetAppService(ActionEvent event) {
		SetAppServiceController controller = (SetAppServiceController) loadFXMLPage("AppService Setting", FXMLPage.SET_APP_SERVICE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}
	/**
	 * 打开设置单元测试
	 * 
	 * @param event
	 */
	public void onSetUnitTest(ActionEvent event) {
		SetUnitTestController controller = (SetUnitTestController) loadFXMLPage("UnitTest Setting", FXMLPage.SET_UNIT_TEST, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SetCustom
	 * 
	 * @param event
	 */
	public void onSetCustom(ActionEvent event) {
		SetCustomController controller = (SetCustomController) loadFXMLPage("SetCustom Setting", FXMLPage.SET_CUSTOM, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置CustomProperty
	 * 
	 * @param event
	 */
	public void onSetCustomProperty(ActionEvent event) {
		SetCustomPropertyController controller = (SetCustomPropertyController) loadFXMLPage("SetCustomProperty Setting",
				FXMLPage.SET_CUSTOM_PROPERTY, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}
	// ======================get/set============================
	/**
	 * 获得当前选择数据库的信息
	 * 
	 * @return
	 */
	public DatabaseConfig getSelectedDatabaseConfig() {
		return selectedDatabaseConfig;
	}

	/**
	 * 设置当前选择数据库的信息
	 * 
	 * @param selectedDatabaseConfig
	 */
	public void setSelectedDatabaseConfig(DatabaseConfig selectedDatabaseConfig) {
		this.selectedDatabaseConfig = selectedDatabaseConfig;
	}

	/**
	 * 获得更新数据库选择的配置文件
	 * 
	 * @return
	 */
	public DatabaseConfig getUpdateOfDatabaseConfig() {
		return updateOfDatabaseConfig;
	}

	/**
	 * 设置更新数据库选择的配置文件
	 * 
	 * @param updateOfDatabaseConfig
	 */
	public void setUpdateOfDatabaseConfig(DatabaseConfig updateOfDatabaseConfig) {
		this.updateOfDatabaseConfig = updateOfDatabaseConfig;
	}

	/**
	 * 获得配置信息的名字
	 * 
	 * @return
	 */
	public String getHistoryConfigName() {
		return historyConfigName;
	}

	/**
	 * 设置配置信息的名字
	 * 
	 * @param historyConfigName
	 */
	public void setHistoryConfigName(String historyConfigName) {
		this.historyConfigName = historyConfigName;
	}

	/**
	 * 获得配置信息
	 * 
	 * @return
	 */
	public HistoryConfig getHistoryConfig() {
		return historyConfig;
	}

	/**
	 * 设置配置信息
	 * 
	 * @param historyConfig
	 */
	public void setHistoryConfig(HistoryConfig historyConfig) {
		this.historyConfig = historyConfig;
	}

	/**
	 * 获得当前数据库选择表的名字
	 * 
	 * @return
	 */
	public String getSelectedTableName() {
		return selectedTableName;
	}

	/**
	 * 设置当前数据库选择表的名字
	 * 
	 * @param selectedTableName
	 */
	public void setSelectedTableName(String selectedTableName) {
		this.selectedTableName = selectedTableName;
	}

	/**
	 * 获得模板文件夹现有模板名字
	 * 
	 * @return
	 */
	public List<String> getTemplateNameItems() {
		return templateNameItems;
	}

	/**
	 * 模板文件夹现有模板名字
	 * 
	 * @param templateNameItems
	 */
	public void setTemplateNameItems(List<String> templateNameItems) {
		this.templateNameItems = templateNameItems;
	}

	/**
	 * 设置项目
	 * @param event
	 */
	public void onSetProject(ActionEvent event) {
		LOG.debug("设置项目名...");
		String projectName = txtProjectName.getText();
		txtEntityPackage.setText(entityPackage+"."+projectName+"."+ PathConfig.ENTITY_PACKAGE);
		txtDTOPackage.setText(DTOPackage+"."+projectName+"."+ PathConfig.DTO_PACKAGE);
		txtDOPackage.setText(DOPackage+"."+projectName+"."+ PathConfig.DO_PACKAGE);

		txtEntityDTOPackage.setText(entityDTOPackage +"."+projectName+"."+ PathConfig.ENTITY_DTO_CONVERTOR);
		txtEntityDOPackage.setText(entityDOPackage +"."+projectName+"."+ PathConfig.ENTITY_DO_TRANSLATOR);
		txtSqlPackage.setText(sqlPackage+"."+projectName+"."+ PathConfig.DAO_PACKAGE);
		txtMapperPackage.setText(mapperPackage);
		txtServicePackage.setText(servicePackage+"."+projectName+"."+ PathConfig.REPOSITORY_PACKAGE);
		txtServiceImplPackage.setText(serviceImplPackage+"."+projectName+"."+ PathConfig.REPOSITORY_IMPL_PACKAGE);

		txtAppServicePackage.setText(appServicePackage +"."+projectName+"."+ PathConfig.APP_SERVICE_PACKAGE);
		txtCommandPackage.setText(commandPackage +"."+projectName+"."+ PathConfig.COMMAND_PACKAGE );
		txtQueryPackage.setText(queryPackage +"."+projectName+"."+ PathConfig.QUERY_PACKAGE );

		txtParamPackage.setText(paramPackage +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_PARAM);
		txtVoPackage.setText(voPackage +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_VO);
		txtRouterConvertorPackage.setText(routerConvertorPackage +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_CONVERTOR);
		txtUnitTestPackage.setText(unitTestPackage  +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_UNIT_TEST);
		txtRouterPackage.setText(routerPackage +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_CONTROLLER);
		txtRouterImplPackage.setText(paramPackage +"."+projectName+"."+ PathConfig.CONTROLLER_ADMIN_CONTROLLER_IMPL);

		txtCParamPackage.setText(cParamPackage +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_PARAM);
		txtCVoPackage.setText(cVoPackage +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_VO);
		txtCRouterConvertorPackage.setText(cRouterConvertorPackage +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_CONVERTOR);
		txtCUnitTestPackage.setText(cUnitTestPackage  +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_UNIT_TEST);
		txtCRouterPackage.setText(cRouterPackage +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_CONTROLLER);
		txtCRouterImplPackage.setText(cParamPackage +"."+projectName+"."+ PathConfig.CONTROLLER_CUSTOMER_CONTROLLER_IMPL);

	}

	public void onSetDO(ActionEvent event) {

	}


	public void onSetEntityDO(ActionEvent event) {

	}

	public void onSetEntityDTO(ActionEvent event) {

	}

	public void onSetParam(ActionEvent actionEvent) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetParamAttributeController controller = (SetParamAttributeController) loadFXMLPage("Param Attribute Setting",
				FXMLPage.SET_PARAM_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	public void onSetVO(ActionEvent actionEvent) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetVOAttributeController controller = (SetVOAttributeController) loadFXMLPage("VO Attribute Setting",
				FXMLPage.SET_VO_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	public void onSetCRouter(ActionEvent actionEvent) {
		SetCRouterController controller = (SetCRouterController) loadFXMLPage("c controller Attribute Setting",
				FXMLPage.SET_ROUTER_C, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	public void onSetCParam(ActionEvent actionEvent) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetCParamAttributeController controller = (SetCParamAttributeController) loadFXMLPage("c Param Attribute Setting",
				FXMLPage.SET_PARAM_C_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	public void onSetCVO(ActionEvent actionEvent) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetCVOAttributeController controller = (SetCVOAttributeController) loadFXMLPage("c VO Attribute Setting",
				FXMLPage.SET_VO_C_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}
}

