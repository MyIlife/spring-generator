package ${content.serviceImpl.classPackage};
import java.util.ArrayList;
import java.util.List;

import com.youzan.i18n.common.model.Page;
import com.youzan.i18n.common.result.ListResultM;
import com.youzan.i18n.common.result.PlainResultM;
import org.springframework.stereotype.Repository;

import ${content.entityDO.classPackage}.${content.entityDO.className};
import ${content.service.classPackage}.${content.service.className};
import ${content.dao.classPackage}.${content.dao.className};
import ${content.DO.classPackage}.${content.DO.className};
import ${content.entity.classPackage}.${content.entity.className};

import javax.annotation.Resource;
/**
 * ${content.entity.className}的服务接口的实现类
 * 
 * @author 
 *
 */
@Repository
public class ${content.serviceImpl.className} implements ${content.service.className} {

	@Resource
	private ${content.dao.className} ${content.dao.className?uncap_first};

	@Override
	public PlainResultM<${content.entity.className}> ${content.service.item.selectOne.value}(${content.entity.className} param){
		${content.DO.className} query = ${content.entityDO.className}.convert2DoFromEntity(param);

		${content.DO.className} resultDO = ${content.dao.className?uncap_first}.${content.dao.item.selectOne.value}(query);

		if(resultDO != null){
			${content.entity.className} resultEntity = ${content.entityDO.className}.convert2EntityFromDo(resultDO);
			return PlainResultM.of(resultEntity);
		}

		return PlainResultM.empty();
	}

	@Override
	public ListResultM<${content.entity.className}> ${content.service.item.selectByParam.value}(${content.entity.className} param, Page page){
		${content.DO.className} queryParam = ${content.entityDO.className}.convert2DoFromEntity(param);

		int sum = 0;

		if(page != null){
			sum = ${content.dao.className?uncap_first}.${content.dao.item.countByParam.value}(queryParam);
		}

		List<${content.DO.className}> resultDOList = ${content.dao.className?uncap_first}.${content.dao.item.selectByParam.value}(queryParam,
				page == null? null : page.getOffset(),
				page == null? null : page.getPageSize());

		return ListResultM.of(${content.entityDO.className}.convert2EntitiesFromDos(resultDOList), sum);
	}

	@Override
	public PlainResultM<Boolean> ${content.service.item.insertByBatch.value}(List<${content.entity.className}> list){
		List<${content.DO.className}>insertParam =  ${content.entityDO.className}.convert2DosFromEntities(list);
		int count = ${content.dao.className?uncap_first}.${content.dao.item.insertByBatch.value}(insertParam);

		return PlainResultM.of(Boolean.TRUE);

	}

	@Override
	public PlainResultM<Boolean> ${content.service.item.updateByKdtId.value}(${content.entity.className} param){
		${content.DO.className} updateParam = ${content.entityDO.className}.convert2DoFromEntity(param);
		int count = ${content.dao.className?uncap_first}.${content.dao.item.updateByKdtId.value}(updateParam);

		return PlainResultM.of(Boolean.TRUE);
	}

	@Override
	public PlainResultM<Boolean> ${content.service.item.deleteByParam.value}(${content.entity.className} param){
		${content.DO.className} deleteParam = ${content.entityDO.className}.convert2DoFromEntity(param);
		int count = ${content.dao.className?uncap_first}.${content.dao.item.deleteByParam.value}(deleteParam);

		return PlainResultM.of(Boolean.TRUE);
	}
}