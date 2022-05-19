package ${content.CControllerImpl.classPackage};

import com.youzan.i18n.aspect.base.BaseOnlineController;
import com.youzan.i18n.aspect.annotation.WithoutLogin;
import com.youzan.api.common.response.ListResult;
import com.youzan.api.common.response.Paginator;
import com.youzan.api.common.response.PaginatorResult;
import com.youzan.api.common.response.PlainResult;

import com.youzan.i18n.common.result.ListResultM;
import com.youzan.i18n.common.result.PlainResultM;
import ${content.CParam.classPackage}.${content.CParam.className};
import ${content.CVO.classPackage}.${content.CVO.className};
import ${content.command.classPackage}.${content.command.className};
import ${content.query.classPackage}.${content.query.className};
import ${content.DTO.classPackage}.${content.DTO.className};
import ${content.CRouterConvertor.classPackage}.${content.CRouterConvertor.className};

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
/**
 * ${content.CController.className}的服务接口的实现类
 *
 * @author
 *
 */
@Service
public class ${content.CControllerImpl.className} extends BaseOnlineController implements ${content.CController.className} {

	@Resource
	private ${content.appService.className} ${content.appService.className?uncap_first};
	
	@Override
	public PlainResult<${content.CVO.className}> ${content.CController.item.selectOne.value}(@Validated ${content.CParam.className} param){
		${content.query.className} query = ${content.CRouterConvertor.className}.convert2QueryFromParam(param);
		
		PlainResultM<${content.DTO.className}> plainResultM = ${content.appService.className?uncap_first}.${content.appService.item.selectOne.value}(query);
		
		return plainResultM.map(${content.CRouterConvertor.className}::convert2VOFromDTO);	
	}
		
	@Override
	public ListResult<${content.CVO.className}> ${content.CController.item.selectByParam.value}(@Validated ${content.CParam.className} param){
		${content.query.className} query = ${content.CRouterConvertor.className}.convert2QueryFromParam(param);
		
		ListResultM<${content.DTO.className}> listResultM = ${content.appService.className?uncap_first}.${content.appService.item.selectByParam.value}(query);
		
		return listResultM.map(${content.CRouterConvertor.className}::convert2VOsFromDTOs);
	}
		
		
	@Override
	public PlainResult<Boolean> ${content.CController.item.updateByKdtId.value}(@Validated ${content.CParam.className} param){
		${content.command.className} command = ${content.CRouterConvertor.className}.convert2CommandFromParam(param);
	
		return ${content.appService.className?uncap_first}.${content.appService.item.updateByKdtId.value}(command);		
	}
		
	@Override
	public PlainResult<Boolean> ${content.CController.item.deleteByParam.value}(@Validated ${content.CParam.className} param){
		${content.command.className} command = ${content.CRouterConvertor.className}.convert2CommandFromParam(param);

		return ${content.appService.className?uncap_first}.${content.appService.item.deleteByParam.value}(command);
	}

}