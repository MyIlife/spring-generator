package ${content.query.classPackage};

import com.youzan.i18n.common.model.Page;

import lombok.Data;

/**
 * ${content.query.tableName}实体类
 * 
 * @author 
 *
 */
@Data
public class ${content.query.className} {
	<#list content.query.noIdAttrs as item>
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>

    /** 分页参数 */
    private Page page;
}
