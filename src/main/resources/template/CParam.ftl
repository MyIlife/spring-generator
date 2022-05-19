package ${content.CParam.classPackage};

import lombok.Data;

/**
 * ${content.CParam.tableName}参数类
 * 
 * @author 
 *
 */
@Data
public class ${content.CParam.className} {
	<#list content.CParam.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
