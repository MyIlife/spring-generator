package ${content.command.classPackage};

import lombok.Data;

/**
 * ${content.command.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.command.className} {
	<#list content.command.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
