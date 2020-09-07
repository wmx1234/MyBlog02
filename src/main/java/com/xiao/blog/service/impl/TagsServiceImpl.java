package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.common.Constant;
import com.xiao.blog.model.Comment;
import com.xiao.blog.util.CommonUtils;
import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.exception.assertion.BusinessExceptionAssert;
import com.xiao.blog.mapper.TagsMapper;
import com.xiao.blog.model.Tags;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.vo.TagsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author wangmx
 * @create 2019-11-30 10:04
 * @Desc
 */
@Service("TagsService")
public class TagsServiceImpl implements TagsService {

    @Resource
    TagsMapper tagsMapper;

    @Override
    public int save(Tags tags) throws BusinessException {

        //校验标签是否已存在
        BusinessExceptionAssert.assertTagsExist(tags);

        if(tags.getId() != null){
            return update(tags);
        } else{
            return insert(tags);
        }

    }

    @Override
    public int delete(Integer id) {
        return tagsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TagsVO> getTagsVOList() {

        List<TagsVO> tagsVOList = tagsMapper.getTagsVOList();

        tagsVOList.forEach(tags->{
            tags.setLink("/tags/"+tags.getId());
            tags.setColor(Constant.COLORS[new Random().nextInt(9)]);
        });

        return tagsVOList;
    }

    @Override
    public List<Tags> getTagsList() {
        return tagsMapper.getAllTags();
    }

    @Override
    public int getTagsCount(){
        return tagsMapper.getTagsCount();
    };


    private int insert(Tags tags){


        tags.setId(DataBaseUtil.nextValue());
        return tagsMapper.insert(tags);
    }

    private int update(Tags tags){

        return tagsMapper.updateByPrimaryKey(tags);
    }
}
