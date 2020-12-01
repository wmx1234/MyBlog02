package com.xiao.blog.service.impl;

import com.xiao.blog.constants.ShiroConstants;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.BoxCategoriesMapper;
import com.xiao.blog.mapper.UserMapper;
import com.xiao.blog.model.User;
import com.xiao.blog.service.BoxService;
import com.xiao.blog.service.UserService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.BoxCategoriesVO;
import com.xiao.blog.vo.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:34
 * @Desc
 */
@Service("boxService")
public class BoxServiceImpl implements BoxService {

    @Resource
    BoxCategoriesMapper boxCategoriesMapper;

    @Override
    public List<BoxCategoriesVO> getBoxCategoriesVO() {
        return boxCategoriesMapper.getBoxCategories();
    }
}
