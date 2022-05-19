package ${content.param.classPackage};

import lombok.Data;

/**
 * ${content.param.tableName}参数类
 * 
 * @author 
 *
 */
@Data
public class ${content.param.className} {
	<#list content.param.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
