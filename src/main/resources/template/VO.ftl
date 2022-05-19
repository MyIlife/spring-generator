package ${content.VO.classPackage};

import lombok.Data;

/**
 * ${content.VO.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.VO.className} {
	<#list content.VO.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

}
