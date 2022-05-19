package ${content.controllerImpl.classPackage};

import com.youzan.i18n.aspect.base.BaseAdminController;
import com.youzan.api.common.response.ListResult;
import com.youzan.api.common.response.Paginator;
import com.youzan.api.common.response.PaginatorResult;
import com.youzan.api.common.response.PlainResult;

import com.youzan.i18n.common.result.ListResultM;
import com.youzan.i18n.common.result.PlainResultM;
import ${content.param.classPackage}.${content.param.className};
import ${content.VO.classPackage}.${content.VO.className};
import ${content.command.classPackage}.${content.command.className};
import ${content.query.classPackage}.${content.query.className};
import ${content.DTO.classPackage}.${content.DTO.className};
import ${content.routerConvertor.classPackage}.${content.routerConvertor.className};

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
/**
 * ${content.controller.className}的服务接口的实现类
 * 
 * @author 
 *
 */
@Service
public class ${content.controllerImpl.className} extends BaseAdminController implements ${content.controller.className} {

	@Resource
	private ${content.appService.className} ${content.appService.className?uncap_first};

	@Override
	public PlainResult<${content.VO.className}> ${content.controller.item.selectOne.value}(@Validated ${content.param.className} param){
		${content.query.className} query = ${content.routerConvertor.className}.convert2QueryFromParam(param);
		query.setKdtId(getLoginKdtId());

		PlainResultM<${content.DTO.className}> plainResultM = ${content.appService.className?uncap_first}.${content.appService.item.selectOne.value}(query);

		return plainResultM.map(${content.routerConvertor.className}::convert2VOFromDTO);
	}

	@Override
	public ListResult<${content.VO.className}> ${content.controller.item.selectByParam.value}(@Validated ${content.param.className} param){
		${content.query.className} query = ${content.routerConvertor.className}.convert2QueryFromParam(param);
		query.setKdtId(getLoginKdtId());

		ListResultM<${content.DTO.className}> listResultM = ${content.appService.className?uncap_first}.${content.appService.item.selectByParam.value}(query);

		return listResultM.map(${content.routerConvertor.className}::convert2VOsFromDTOs);
	}


	@Override
	public PlainResult<Boolean> ${content.controller.item.updateByKdtId.value}(@Validated ${content.param.className} param){
		${content.command.className} command = ${content.routerConvertor.className}.convert2CommandFromParam(param);
		command.setKdtId(getLoginKdtId());

		return ${content.appService.className?uncap_first}.${content.appService.item.updateByKdtId.value}(command);
	}

	@Override
	public PlainResult<Boolean> ${content.controller.item.deleteByParam.value}(@Validated ${content.param.className} param){
		${content.command.className} command = ${content.routerConvertor.className}.convert2CommandFromParam(param);
		command.setKdtId(getLoginKdtId());

		return ${content.appService.className?uncap_first}.${content.appService.item.deleteByParam.value}(command);
	}
}