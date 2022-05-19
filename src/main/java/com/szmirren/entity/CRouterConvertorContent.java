package com.szmirren.entity;

/**
 * c端convertor的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class CRouterConvertorContent {
	/** EntityDTO转换器类的包名 */
	private String classPackage;
	/** EntityDTO转换器类的类型 */
	private String className;

	/**
	 * 初始化
	 */
	public CRouterConvertorContent() {
		super();
	}

	/**
	 * 通过包名与类名初始化
	 *
	 * @param classPackage
	 * @param className
	 */
	public CRouterConvertorContent(String classPackage, String className) {
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
