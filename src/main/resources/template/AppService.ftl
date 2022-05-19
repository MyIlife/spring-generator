package ${content.serviceImpl.classPackage};
import java.util.List;

import com.youzan.i18n.common.result.ListResultM;
import com.youzan.i18n.common.result.PlainResultM;
import ${content.command.classPackage}.${content.command.className};
import ${content.entityDTO.classPackage}.${content.entityDTO.className};
import ${content.query.classPackage}.${content.query.className};
import ${content.DTO.classPackage}.${content.DTO.className};
import ${content.entity.classPackage}.${content.entity.className};
import ${content.service.classPackage}.${content.service.className};

import org.springframework.stereotype.Service;
/**
 * ${content.entity.className}的服务接口的实现类
 * 
 * @author 
 *
 */
@Service
public class ${content.appService.className} {

	@Resource
	private ${content.service.className} ${content.service.className?uncap_first};

	/**
	 * ${content.appService.item.selectOne.describe}
	 * @param query 查询条件
	 * @return 单条数据
	 */
	public PlainResultM<${content.DTO.className}> ${content.appService.item.selectOne.value}(${content.query.className} query){
		${content.entity.className} entityQuery = ${content.entityDTO.className}.convert2EntityFromQuery(query);

		PlainResultM<${content.entity.className}> resultM = ${content.service.className?uncap_first}.${content.service.item.selectOne.value}(entityQuery);

		return resultM.map(${content.entityDTO.className}::convert2DTOFromEntity);
	}

	/**
	 * ${content.appService.item.selectByParam.describe}
	 * @param query 查询条件
	 * @param page 分页参数
	 * @return 多条数据
	 */
	public ListResultM<${content.DTO.className}> ${content.appService.item.selectByParam.value}(${content.query.className} query){
		${content.entity.className} entityQuery = ${content.entityDTO.className}.convert2EntityFromQuery(query);

		ListResultM<${content.entity.className}> listResultM = ${content.service.className?uncap_first}.${content.service.item.selectByParam.value}(entityQuery,query.getPage());

		return listResultM.flatMap(list -> {
		List<${content.DTO.className}> dtoList = ${content.entityDTO.className}.convert2DTOsFromEntities(list);
			return ListResultM.of(dtoList, listResultM.getCount());
		});

	}

	/**
	 * ${content.appService.item.insertByBatch.describe}
	 * @param list
	 * @return 插入结果
	 */
	public PlainResultM<Boolean> ${content.appService.item.insertByBatch.value}(List<${content.command.className}> commandList){
		List<${content.entity.className}>insertList =  ${content.entityDTO.className}.convert2EntitiesFromCommands(commandList);

		return ${content.service.className?uncap_first}.${content.service.item.insertByBatch.value}(insertList);


	}

	/**
	 * ${content.appService.item.updateByKdtId.describe}
	 * @param command 参数
	 * @return 更新结果
	 */
	public PlainResultM<Boolean> ${content.appService.item.updateByKdtId.value}(${content.command.className} command){
		${content.entity.className} entity = ${content.entityDTO.className}.convert2EntityFromCommand(command);

		return ${content.service.className?uncap_first}.${content.service.item.updateByKdtId.value}(entity);
	}

	/**
	 * ${content.appService.item.deleteByParam.describe}
	 * @param command
	 * @return 删除结果
	 */
	public PlainResultM<Boolean> ${content.appService.item.deleteByParam.value}(${content.command.className} command){
		${content.entity.className} entity = ${content.entityDTO.className}.convert2EntityFromCommand(command);

		return ${content.service.className?uncap_first}.${content.service.item.deleteByParam.value}(entity);
	}
}