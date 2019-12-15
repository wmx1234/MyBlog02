package com.xiao.blog.pojo.param;

/**
 * @author wangmx
 * @create 2019-12-01 15:27
 * @Desc
 */


import cn.hutool.json.JSONUtil;
import com.xiao.blog.util.CastUtil;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Params {
    protected final Map<String, Object> fieldMap;

    public Params() {
        fieldMap = new HashMap();
        fieldMap.clear();
    }

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }


    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public Object get(String name) {
        if (fieldMap == null) {
            return null;
        }
        return fieldMap.get(name);
    }

    public String getString(String name) {
        return CastUtil.castString(get(name), null);
    }

    public String getString(String name, String defaultV) {
        return CastUtil.castString(get(name), defaultV);
    }

    public String getString2(String name) {
        return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(get(name));
    }

    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(get(name));
    }

    public boolean getBoolean(String name, boolean defalutValue) {
        return CastUtil.castBoolean(get(name), defalutValue);
    }

    public <T> T getObject(String name, Class<T> clazz) {


        String s = JSONUtil.toJsonStr(get(name));


        T per = (T)JSONUtil.toBean(s,clazz);; //将json转成需要的对象


        return per;
    }

    public List getList(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }
        if ((obj instanceof List)) {
            return (List) obj;
        }
        List list = JSONUtil.toBean(getString(name), List.class);
        return list;
    }

    public List<Map> getListMap(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }
        if ((obj instanceof List)) {
            return (List) obj;
        }
        List list = (List) JSONUtil.toBean(getString(name), List.class);
        return list;
    }

    public Map getMap(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Map)) {
            return (Map) obj;
        }
        Map map = (Map) JSONUtil.toBean(getString(name), Map.class);
        return map;
    }


    public Map getMapByKeys(String... keys) {
        Map rtnMap = new HashMap();
        for (String key : keys) {
            rtnMap.put(key, get(key));
        }
        return rtnMap;
    }

    public void put(String name, Object value) {
        fieldMap.put(name, value);
    }

    public void remove(String key) {
        fieldMap.remove(key);
    }

    public Map<String, Object> getCloneMap() {
        Map map = new HashMap();
        if (fieldMap != null) {
            for (String key : fieldMap.keySet()) {
                map.put(key, fieldMap.get(key));
            }
        }
        return map;
    }

    public <T> T toObject(Class<T> beanClass) {
        Object obj = null;
        try {
            obj = beanClass.newInstance();

            ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
            convertUtilsBean.deregister(Date.class);
            convertUtilsBean.register(new DateConverter(), Date.class);

            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
            beanUtilsBean.populate(obj, fieldMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }
}


