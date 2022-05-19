package ${content.DTO.classPackage};

import lombok.Data;

/**
 * ${content.DTO.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.DTO.className} {
	<#list content.DTO.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
