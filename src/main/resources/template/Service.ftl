package ${content.service.classPackage};

import com.youzan.i18n.common.model.Page;
import com.youzan.i18n.common.result.ListResultM;
import com.youzan.i18n.common.result.PlainResultM;
import ${content.entity.classPackage}.${content.entity.className};

import java.util.List;



/**
 * ${content.entity.className}的服务接口
 * 
 * @author 
 *
 */
public interface ${content.service.className} {

	/**
	 * ${content.service.item.selectOne.describe}
	 * @param param 查询条件
	 * @return 单条数据
	 */
	PlainResultM<${content.entity.className}> ${content.service.item.selectOne.value}(${content.entity.className} param);

	/**
	 * ${content.service.item.selectByParam.describe}
	 * @param param 查询条件
	 * @param page 分页参数
     * @return 多条数据
	 */
	ListResultM<${content.entity.className}> ${content.service.item.selectByParam.value}(${content.entity.className} param, Page page);

	/**
	 * ${content.service.item.insertByBatch.describe}
	 * @param list
	 * @return 插入结果
	 */
	PlainResultM<Boolean> ${content.service.item.insertByBatch.value}(List<${content.entity.className}> list);

	/**
	 * ${content.service.item.updateByKdtId.describe}
	 * @param param 参数
	 * @return 更新结果
	 */
	PlainResultM<Boolean> ${content.service.item.updateByKdtId.value}(${content.entity.className} param);

	/**
	 * ${content.service.item.deleteByParam.describe}
	 * @param param
	 * @return 删除结果
	 */
	PlainResultM<Boolean> ${content.service.item.deleteByParam.value}(${content.entity.className} param);
}
