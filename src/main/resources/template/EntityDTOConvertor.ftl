package ${content.entityDTO.classPackage};

import com.youzan.i18n.common.util.BeanUtils;
import ${content.command.classPackage}.${content.command.className};
import ${content.DTO.classPackage}.${content.DTO.className};
import ${content.query.classPackage}.${content.query.className};
import ${content.entity.classPackage}.${content.entity.className};
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * ${content.DTO.className} / ${content.entity.className} 转换器类
 * 
 * @author 
 *
 */
public class ${content.entityDTO.className} {

    public static ${content.DTO.className} convert2DTOFromEntity(${content.entity.className} entity){
        if(Objects.isNull(entity)){
            return null;
        }
        return BeanUtils.copyProperty(entity,${content.DTO.className}.class);

    }

    public static List<${content.DTO.className}> convert2DTOsFromEntities(List<${content.entity.className}> entities){
        if(CollectionUtils.isEmpty(entities)){
            return Collections.emptyList();
        }
        List<${content.DTO.className}> resultList = new ArrayList<>();
        for(${content.entity.className} entity : entities){
            resultList.add(convert2DTOFromEntity(entity));
        }
        return resultList;
    }


    public static ${content.entity.className} convert2EntityFromDTO(${content.DTO.className} dto){
        if(Objects.isNull(dto)){
            return null;
        }
        return BeanUtils.copyProperty(dto,${content.entity.className}.class);

    }

    public static List<${content.entity.className}> convert2EntitiesFromDTOs(List<${content.DTO.className}> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }
        List<${content.entity.className}> resultList = new ArrayList<>();
        for(${content.DTO.className} dto : dtoList){
            resultList.add(convert2EntityFromDTO(dto));
        }
        return resultList;
    }

    public static ${content.entity.className} convert2EntityFromQuery(${content.query.className} query){
        if(Objects.isNull(query)){
            return null;
        }

        return BeanUtils.copyProperty(query,${content.entity.className}.class);
    }

    public static ${content.entity.className} convert2EntityFromCommand(${content.command.className} command){
        if(Objects.isNull(command)){
            return null;
        }

        return BeanUtils.copyProperty(command,${content.entity.className}.class);
    }

    public static List<${content.entity.className}> convert2EntitiesFromCommands(List<${content.command.className}> commandList){
        if(CollectionUtils.isEmpty(commandList)){
            return Collections.emptyList();
        }
        List<${content.entity.className}> resultList = new ArrayList<>();
        for(${content.command.className} command : commandList){
            resultList.add(convert2EntityFromCommand(command));
        }
        return resultList;
    }
}
