package com.xiao.blog.util;

import com.hankcs.hanlp.HanLP;

import java.util.List;
import java.util.Random;

/**
 * @author wangmx
 * @create 2019-12-08 20:39
 * @Desc
 */
public class ArticleUtil {

    public static String  extractSummary(String htmlArticleComment){

        List<String> summaryList= HanLP.extractSummary(htmlArticleComment,5);

        String summary = String.join(",", summaryList);

        return summary;
    }

    /**
     * 获取文章简介
     * @param htmlArticleComment
     * @return
     */
    public static String buildArticleTabloid(String htmlArticleComment){

        String regex = "\\s+";
        String str = htmlArticleComment.trim();
        //去掉所有空格
        String articleTabloid = str.replaceAll(regex,"");

        int beginIndex = articleTabloid.indexOf("<");
        int endIndex = articleTabloid.indexOf(">");
        String myArticleTabloid = "";
        String nowStr = "";
        while (beginIndex != -1){
            nowStr = articleTabloid.substring(0, beginIndex);
            myArticleTabloid += nowStr;

            articleTabloid = articleTabloid.substring(endIndex + 1);
            beginIndex = articleTabloid.indexOf("<");
            if(myArticleTabloid.length() < 197){
                //过滤掉<pre>标签中的代码块
                if(articleTabloid.length() > 4){
                    if(articleTabloid.charAt(beginIndex) == '<' && articleTabloid.charAt(beginIndex+1) == 'p'  && articleTabloid.charAt(beginIndex+2) == 'r'  && articleTabloid.charAt(beginIndex+3) == 'e' ){
                        endIndex = articleTabloid.indexOf("</pre>");
                        endIndex = endIndex + 5;
                    } else {
                        endIndex = articleTabloid.indexOf(">");
                    }
                } else {
                    endIndex = articleTabloid.indexOf(">");
                }
            } else {
                break;
            }

        }

        return myArticleTabloid;
    }

    /**
     * 获取文章随机图片
     * @return
     */
    public static String randomImage() {
        return "https://cdn.jsdelivr.net/gh/wangmx996/wangmx996.github.io/medias/featureimages/"+new Random().nextInt(18)+".jpg";
    }
}

