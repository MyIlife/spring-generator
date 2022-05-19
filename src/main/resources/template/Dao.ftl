package ${content.dao.classPackage};
import java.util.List;

import ${content.DO.classPackage}.${content.DO.className};
import org.apache.ibatis.annotations.Param;

/**
 * ${content.DO.className}的Dao接口
 * 
 * @author 
 *
 */
public interface ${content.dao.className} {

	/**
	 * ${content.dao.item.selectOne.describe}
	 * @param param 参数
	 * @return 单条数据
	 */
	${content.DO.className} ${content.dao.item.selectOne.value}(@Param("param") ${content.DO.className} param);

	/**
	 * ${content.dao.item.selectByParam.describe}
	 * @param param 参数
	 * @param offset 分页偏移量
	 * @param pageSize 分页大小
     * @return list
	 */
	List<${content.DO.className}> ${content.dao.item.selectByParam.value}(@Param("param") ${content.DO.className} param,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	/**
	 * ${content.dao.item.countByParam.describe}
	 * @param param 参数
	 * @return 数量
	 */
	int ${content.dao.item.countByParam.value}(@Param("param") ${content.DO.className} param);

	/**
	 * ${content.dao.item.insertByBatch.describe}
	 * @param list list
	 * @return 数量
	 */
	int ${content.dao.item.insertByBatch.value}(List<${content.DO.className}> list);

	/**
	 * ${content.dao.item.updateByKdtId.describe}
	 * @param param 参数
	 * @return 数量
	 */
	int ${content.dao.item.updateByKdtId.value}(@Param("param") ${content.DO.className} param);

	/**
	 * ${content.dao.item.deleteByParam.describe}
	 * @param param
     * @return 数量
	 */
	int ${content.dao.item.deleteByParam.value}(@Param("param") ${content.DO.className} param);

}