package ${content.controller.classPackage};

import com.youzan.api.common.response.ListResult;
import com.youzan.api.common.response.PlainResult;
import ${content.param.classPackage}.${content.param.className};
import ${content.VO.classPackage}.${content.VO.className};

/**
 * ${content.controller.className} b端接口服务
 * 
 * @author 
 *
 */

public interface ${content.controller.className} {

	/**
	 * ${content.controller.item.selectOne.describe}
	 * @param param 查询条件
	 * @return 单条数据
	 */
	PlainResult<${content.VO.className}> ${content.controller.item.selectOne.value}(${content.param.className} param);

	/**
	 * ${content.controller.item.selectByParam.describe}
	 * @param param 查询条件
	 * @return 多条数据
	 */
	ListResult<${content.VO.className}> ${content.controller.item.selectByParam.value}(${content.param.className} param);

	/**
	 * ${content.controller.item.updateByKdtId.describe}
	 * @param param 参数
	 * @return 更新结果
	 */
	PlainResult<Boolean> ${content.controller.item.updateByKdtId.value}(${content.param.className} param);

	/**
	 * ${content.controller.item.deleteByParam.describe}
	 * @param param
	 * @return 删除结果
	 */
	PlainResult<Boolean> ${content.controller.item.deleteByParam.value}(${content.param.className} param);

}
