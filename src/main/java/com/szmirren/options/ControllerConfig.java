package com.szmirren.options;

import java.util.ArrayList;
import java.util.List;

import com.szmirren.common.Constant;
import com.szmirren.models.TableAttributeKeyValue;

import javafx.collections.ObservableList;

/**
 * Router属性的配置文件
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class ControllerConfig {
	/** 设置的tableItem */
	private List<TableAttributeKeyValue> tableItem = new ArrayList<>();
	/** 生成模板的名字 */
	private String templateName = Constant.TEMPLATE_NAME_ROUTER;
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	/**
	 * 初始化
	 */
	public ControllerConfig() {
		super();
	}

	/**
	 * 通过 ObservableList<TableAttributeKeyValue>初始化
	 * 
	 * @param tableItem
	 */
	public ControllerConfig(ObservableList<TableAttributeKeyValue> item) {
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
	public ControllerConfig(ObservableList<TableAttributeKeyValue> item, String templateName, boolean overrideFile) {
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
	public ControllerConfig initDefaultValue() {
		tableItem.add(new TableAttributeKeyValue("selectOne", "selectOne", "条件查询一条数据"));
		tableItem.add(new TableAttributeKeyValue("selectByParam", "selectByParam", "条件查询所有数据"));
		tableItem.add(new TableAttributeKeyValue("pageByParam", "searchByParam", "分页查询所有数据"));
		tableItem.add(new TableAttributeKeyValue("insertByBatch", "insertByBatch", "批量插入"));
		tableItem.add(new TableAttributeKeyValue("updateByKdtId", "updateByKdtId", "根据店铺id更新数据"));
		tableItem.add(new TableAttributeKeyValue("deleteByParam", "deleteByParam", "根据条件删除数据"));


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
		return "RouterConfig [tableItem=" + tableItem + ", templateName=" + templateName + ", overrideFile=" + overrideFile + "]";
	}

}
