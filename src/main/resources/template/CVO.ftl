package ${content.CVO.classPackage};

import lombok.Data;

/**
 * ${content.CVO.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.CVO.className} {
	<#list content.CVO.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
