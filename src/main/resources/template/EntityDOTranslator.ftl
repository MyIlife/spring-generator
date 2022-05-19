package ${content.entityDO.classPackage};

import com.youzan.i18n.common.util.BeanUtils;
import ${content.DO.classPackage}.${content.DO.className};
import ${content.entity.classPackage}.${content.entity.className};
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * ${content.DO.className} / ${content.entity.className} 转换器类
 * 
 * @author 
 *
 */
public class ${content.entityDO.className} {

    public static ${content.DO.className} convert2DoFromEntity(${content.entity.className} entity){
        if(Objects.isNull(entity)){
            return null;
        }
        return BeanUtils.copyProperty(entity,${content.DO.className}.class);
    }

    public static List<${content.DO.className}> convert2DosFromEntities(List<${content.entity.className}> entities){
        if(CollectionUtils.isEmpty(entities)){
            return Collections.emptyList();
        }
        List<${content.DO.className}> resultList = new ArrayList<>();
        for(${content.entity.className} entity : entities){
            resultList.add(convert2DoFromEntity(entity));
        }
        return resultList;
    }




    public static ${content.entity.className} convert2EntityFromDo(${content.DO.className} doObject){
        if(Objects.isNull(doObject)){
            return null;
        }
        return BeanUtils.copyProperty(doObject,${content.entity.className}.class);

    }

    public static List<${content.entity.className}> convert2EntitiesFromDos(List<${content.DO.className}> dos){
        if(CollectionUtils.isEmpty(dos)){
            return Collections.emptyList();
        }
        List<${content.entity.className}> resultList = new ArrayList<>();
        for(${content.DO.className} doObject : dos){
            resultList.add(convert2EntityFromDo(doObject));
        }
        return resultList;
    }
}
