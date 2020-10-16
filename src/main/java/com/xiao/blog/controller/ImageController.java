package com.xiao.blog.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.json.JSONObject;
import com.xiao.blog.shiro.ShiroKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject upload (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String trueFileName = file.getOriginalFilename();

        String fileName = System.currentTimeMillis() + trueFileName;

        String path = "/data/images/"+ ShiroKit.getSessionAttr("articleId");

        File targetFile = new File(path, fileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject res = new JSONObject();
        res.set("url", "/images/"+ ShiroKit.getSessionAttr("articleId") + File.separator + fileName);
        res.set("success", 1);
        res.set("message", "upload success!");

        return res;

    }


}
