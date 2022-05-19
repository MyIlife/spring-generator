package ${content.CController.classPackage};

import com.youzan.api.common.response.ListResult;
import com.youzan.api.common.response.PlainResult;
import ${content.CParam.classPackage}.${content.CParam.className};
import ${content.CVO.classPackage}.${content.CVO.className};

/**
* ${content.CController.className} c端接口服务
*
* @author
*
*/

public interface ${content.CController.className} {

	/**
	 * ${content.CController.item.selectOne.describe}
	 * @param param 查询条件
	 * @return 单条数据
	 */
	PlainResult<${content.CVO.className}> ${content.CController.item.selectOne.value}(${content.CParam.className} param);

	/**
	 * ${content.CController.item.selectByParam.describe}
	 * @param param 查询条件
	 * @return 多条数据
	 */
	ListResult<${content.CVO.className}> ${content.CController.item.selectByParam.value}(${content.CParam.className} param);

	/**
	 * ${content.CController.item.updateByKdtId.describe}
	 * @param param 参数
	 * @return 更新结果
	 */
	PlainResult<Boolean> ${content.CController.item.updateByKdtId.value}(${content.CParam.className} param);

	/**
	 * ${content.CController.item.deleteByParam.describe}
	 * @param param
	 * @return 删除结果
	 */
	PlainResult<Boolean> ${content.CController.item.deleteByParam.value}(${content.CParam.className} param);

}
