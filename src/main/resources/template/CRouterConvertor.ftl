package ${content.CRouterConvertor.classPackage};

import com.youzan.i18n.common.util.BeanUtils;
import ${content.CParam.classPackage}.${content.CParam.className};
import ${content.CVO.classPackage}.${content.CVO.className};
import ${content.command.classPackage}.${content.command.className};
import ${content.DTO.classPackage}.${content.DTO.className};
import ${content.query.classPackage}.${content.query.className};

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * c端controller 转换器类
 * param转 command、query
 * DTO 转 VO
 * @author
 *
 */
public class ${content.CRouterConvertor.className} {

    public static ${content.CVO.className} convert2VOFromDTO(${content.DTO.className} dto){

        return BeanUtils.copyProperty(dto,${content.CVO.className}.class);

    }

    public static List<${content.CVO.className}> convert2VOsFromDTOs(List<${content.DTO.className}> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }

        List<${content.CVO.className}> resultList = new ArrayList<>();
        for(${content.DTO.className} dto : dtoList){
            resultList.add(convert2VOFromDTO(dto));
        }
        return resultList;
    }

    public static ${content.query.className} convert2QueryFromParam(${content.CParam.className} param){

        return BeanUtils.copyProperty(param,${content.query.className}.class);
    }

    public static ${content.command.className} convert2CommandFromParam(${content.CParam.className} param){

        return BeanUtils.copyProperty(param,${content.command.className}.class);
    }

    public static List<${content.command.className}> convert2CommandsFromParams(List<${content.CParam.className}> paramList){
        if(CollectionUtils.isEmpty(paramList)){
            return Collections.emptyList();
        }

        List<${content.command.className}> resultList = new ArrayList<>();
        for(${content.CParam.className} param : paramList){
            resultList.add(convert2CommandFromParam(param));
        }
        return resultList;
    }
}
