package com.szmirren.options;

import com.szmirren.common.Constant;
import com.szmirren.models.TableAttributeKeyValue;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * b端的controller转换器的配置文件
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class RouterConvertorConfig {
	/** 设置的tableItem */
	private List<TableAttributeKeyValue> tableItem = new ArrayList<>();
	/** 生成模板的名字 */
	private String templateName = Constant.TEMPLATE_NAME_ROUTER_CONVERTER;
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	/**
	 * 初始化
	 */
	public RouterConvertorConfig() {
		super();
	}

	/**
	 * 通过 ObservableList<TableAttributeKeyValue>初始化
	 *
	 * @param tableItem
	 */
	public RouterConvertorConfig(ObservableList<TableAttributeKeyValue> item) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
	}

	/**
	 * 通过 ObservableList<TableAttributeKeyValue>初始化
	 *
	 * @param tableItem
	 */
	public RouterConvertorConfig(ObservableList<TableAttributeKeyValue> item, String templateName, boolean overrideFile) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
		this.templateName = templateName;
		this.overrideFile = overrideFile;
	}

	/**
	 * 初始化默认数据
	 */
	public RouterConvertorConfig initDefaultValue() {
		/*tableItem.add(new TableAttributeKeyValue("select", "find{C}", "查询所有数据"));
		tableItem.add(new TableAttributeKeyValue("selectById", "get{C}ById", "通过id查询数据"));
		tableItem.add(new TableAttributeKeyValue("insertNotNull", "saveNotNull{C}", "插入不为空的数据"));
		tableItem.add(new TableAttributeKeyValue("updateNotNull", "updateNotNull{C}", "更新不为空的数据"));
		tableItem.add(new TableAttributeKeyValue("deleteById", "delete{C}ById", "通过id删除数据"));*/
		return this;
	}

	/**
	 * 设置属性集合
	 * 
	 * @return
	 */
	public List<TableAttributeKeyValue> getTableItem() {
		return tableItem;
	}

	/**
	 * 获取属性集合
	 * 
	 * @param tableItem
	 */
	public void setTableItem(List<TableAttributeKeyValue> tableItem) {
		this.tableItem = tableItem;
	}

	/**
	 * 获得模板的名称
	 * 
	 * @return
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模板的名称
	 * 
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取是否覆盖原文件
	 * 
	 * @return
	 */
	public boolean isOverrideFile() {
		return overrideFile;
	}

	/**
	 * 设置是否覆盖原文件
	 * 
	 * @param overrideFile
	 */
	public void setOverrideFile(boolean overrideFile) {
		this.overrideFile = overrideFile;
	}

	@Override
	public String toString() {
		return "UnitTestConfig [tableItem=" + tableItem + ", templateName=" + templateName + ", overrideFile=" + overrideFile + "]";
	}

}
