package ${content.CUnitTest.classPackage};

import com.youzan.api.common.response.BaseResult;
import com.youzan.api.common.response.PlainResult;
import com.youzan.api.common.response.ListResult;
import com.youzan.i18n.aspect.context.AdminLoginInfoContext;
import com.youzan.i18n.aspect.dto.OnlineLoginDTO;
import com.youzan.i18n.common.util.JsonUtils;
import ${content.CParam.classPackage}.${content.CParam.className};
import ${content.CVO.classPackage}.${content.CVO.className};
import ${content.CController.classPackage}.${content.CController.className};

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * c端 ${content.CController.className}的测试
 *
 * @author
 *
 */
@Slf4j
public class ${content.CUnitTest.className} extends BaseTest {
	@Resource
	private ${content.CController.className} ${content.CController.className?uncap_first};

	@Override
	public OnlineLoginDTO mockOnlineData() {
		OnlineLoginDTO onlineDTO = new OnlineLoginDTO();
		onlineDTO.setLoginKdtId(null);
		onlineDTO.setLoginAccountId(0L);

		return onlineDTO;
	}
	/**
	 * 打印执行测试的结果
	 */
	private void print(BaseResult r){
		log.info("执行结果->>{}",JsonUtils.toJson(r));
	}

	/**
	 * ${content.CController.item.selectOne.describe}
	 */
	@Test
	public void ${content.CController.item.selectOne.value}(){

		${content.CParam.className} param = new ${content.CParam.className}();

		PlainResult<${content.CVO.className}> r = ${content.CController.className?uncap_first}.${content.CController.item.selectOne.value}(param);

		print(r);
	}

	/**
	 * ${content.CController.item.selectByParam.describe}
	 */
	@Test
	public void ${content.CController.item.selectByParam.value}(){

		${content.CParam.className} param = new ${content.CParam.className}();

		ListResult<${content.CVO.className}> r = ${content.CController.className?uncap_first}.${content.CController.item.selectByParam.value}(param);

		print(r);
	}

	/**
	 * ${content.CController.item.updateByKdtId.describe}
	 */
	@Test
	public void ${content.CController.item.updateByKdtId.value}(){

		${content.CParam.className} param = new ${content.CParam.className}();

		PlainResult<Boolean> r = ${content.CController.className?uncap_first}.${content.CController.item.updateByKdtId.value}(param);

		print(r);
	}

	/**
	 * ${content.CController.item.deleteByParam.describe}
	 */
	@Test
	public void ${content.CController.item.deleteByParam.value}(){

		${content.CParam.className} param = new ${content.CParam.className}();

		PlainResult<Boolean> r = ${content.CController.className?uncap_first}.${content.CController.item.deleteByParam.value}(param);

		print(r);
	}

}