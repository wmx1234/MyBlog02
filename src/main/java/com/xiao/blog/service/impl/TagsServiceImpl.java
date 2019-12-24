package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.TagsMapper;
import com.xiao.blog.model.Tags;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.shiro.ShiroKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 10:04
 * @Desc
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagsMapper tagsMapper;

    @Override
    public int save(Tags tags) {

        tags.setUserId(ShiroKit.getUser().getId());
        if(tags.getId() != null)
            return update(tags);
        else
            return insert(tags);

    }

    @Override
    public List<Tags> getAll() {

        return tagsMapper.selectAll();
    }

    @Override
    public int deleteBatch(List<Integer> ids) {
        return 0;
    }


    private int insert(Tags label){
        //label.setPublishdate(DateUtil.today());
        label.setUserId(ShiroKit.getUser().getId());
        return tagsMapper.insert(label);
    }

    private int update(Tags label){
        //label.setUpdatedate(DateUtil.today());
        return tagsMapper.updateByPrimaryKey(label);
    }
}
