package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.LabelMapper;
import com.xiao.blog.model.Label;
import com.xiao.blog.model.User;
import com.xiao.blog.service.LabelService;
import com.xiao.blog.shiro.ShiroKit;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 10:04
 * @Desc
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    @Override
    public int save(Label label) {
        label.setUserId(ShiroKit.getUser().getId());
        if(label.getId() != null)
            return update(label);
        else
            return insert(label);

    }

    @Override
    public List<Label> getAll() {

        return labelMapper.selectAll(ShiroKit.getUser().getId());
    }

    @Override
    public int deleteBatch(List<Integer> ids) {
        return labelMapper.deleteBatch(ids);
    }


    private int insert(Label label){
        label.setPublishdate(DateUtil.today());
        label.setUserId(ShiroKit.getUser().getId());
        return labelMapper.insert(label);
    }

    private int update(Label label){
        label.setUpdatedate(DateUtil.today());
        return labelMapper.updateByPrimaryKey(label);
    }
}
