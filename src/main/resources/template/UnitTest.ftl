package ${content.unitTest.classPackage};

import com.youzan.api.common.response.BaseResult;
import com.youzan.api.common.response.PlainResult;
import com.youzan.api.common.response.ListResult;
import com.youzan.i18n.aspect.context.AdminLoginInfoContext;
import com.youzan.i18n.aspect.dto.AdminLoginDTO;
import com.youzan.i18n.common.util.JsonUtils;
import ${content.param.classPackage}.${content.param.className};
import ${content.VO.classPackage}.${content.VO.className};
import ${content.controller.classPackage}.${content.controller.className};

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * b端 ${content.controller.className}的测试
 * 
 * @author 
 *
 */
@Slf4j
public class ${content.unitTest.className} extends BaseTest {
	@Resource
	private ${content.controller.className} ${content.controller.className?uncap_first};

	@Override
	public AdminLoginDTO mockAdminData() {
		AdminLoginDTO adminDTO = new AdminLoginDTO();
		adminDTO.setLoginKdtId(null);
		adminDTO.setLoginAccountId(0L);

		return adminDTO;
	}
	/**
	 * 打印执行测试的结果
	 */
	private void print(BaseResult r){
		log.info("执行结果->>{}",JsonUtils.toJson(r));
	}

	/**
	 * ${content.controller.item.selectOne.describe}
	 */
	@Test
	public void ${content.controller.item.selectOne.value}(){

		${content.param.className} param = new ${content.param.className}();

		PlainResult<${content.VO.className}> r = ${content.controller.className?uncap_first}.${content.controller.item.selectOne.value}(param);

		print(r);
	}

	/**
	 * ${content.controller.item.selectByParam.describe}
	 */
	@Test
	public void ${content.controller.item.selectByParam.value}(){

		${content.param.className} param = new ${content.param.className}();

		ListResult<${content.VO.className}> r = ${content.controller.className?uncap_first}.${content.controller.item.selectByParam.value}(param);

		print(r);
	}

	/**
	 * ${content.controller.item.updateByKdtId.describe}
	 */
	@Test
	public void ${content.controller.item.updateByKdtId.value}(){

		${content.param.className} param = new ${content.param.className}();

		PlainResult<Boolean> r = ${content.controller.className?uncap_first}.${content.controller.item.updateByKdtId.value}(param);

		print(r);
	}

	/**
	 * ${content.controller.item.deleteByParam.describe}
	 */
	@Test
	public void ${content.controller.item.deleteByParam.value}(){

		${content.param.className} param = new ${content.param.className}();

		PlainResult<Boolean> r = ${content.controller.className?uncap_first}.${content.controller.item.deleteByParam.value}(param);

		print(r);
	}
}