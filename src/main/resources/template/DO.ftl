package ${content.DO.classPackage};

import lombok.Data;

/**
 * ${content.DO.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.DO.className} {
	<#list content.DO.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
