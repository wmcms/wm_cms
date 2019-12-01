package com.wilson.cms.utils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.wilson.cms.vo.IEnum;
import com.wilson.cms.vo.ItemVo;

/**
* Enm 枚举工具类
*
*/
public class EnumUtils {

    @SuppressWarnings("rawtypes")
	public static <E extends Enum<E>> List<ItemVo> getEnums(Class<E> clazz) {
        EnumSet<E> es = EnumSet.allOf(clazz);
		List<ItemVo> items = new ArrayList<>();
        for (E e : es) {
            if(e instanceof IEnum){
                IEnum item =(IEnum)e;
                items.add(new ItemVo<Integer>(item.getKey(), item.getText()));
            }
        }
        return items;
    }
}
