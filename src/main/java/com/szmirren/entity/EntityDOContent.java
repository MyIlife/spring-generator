package com.szmirren.entity;

/**
 * EntityDO的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class EntityDOContent {
	/** EntityDO转换器类的包名 */
	private String classPackage;
	/** EntityDO转换器类的类型 */
	private String className;

	/**
	 * 初始化
	 */
	public EntityDOContent() {
		super();
	}

	/**
	 * 通过包名与类名初始化
	 *
	 * @param classPackage
	 * @param className
	 */
	public EntityDOContent(String classPackage, String className) {
		super();
		this.classPackage = classPackage;
		this.className = className;
	}




	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
