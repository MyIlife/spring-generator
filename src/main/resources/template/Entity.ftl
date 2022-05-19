package ${content.entity.classPackage};

import lombok.Data;

/**
 * ${content.entity.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.entity.className} {
	<#list content.entity.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>
	
}
