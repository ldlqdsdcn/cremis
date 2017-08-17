package com.dsdl.eidea.util;

/**
 * mybabis 中like
 *
 * @author 2017-5-31
 */
public class LikeUtil {
    private static String likestr;

    public static String getLike(String likestr) {
        if(StringUtil.isNotEmpty(likestr)){
            likestr = "%"+likestr+"%"   ;
        }
        return likestr;
    }
    public static String getLeftLike(String likestr) {
        if(StringUtil.isNotEmpty(likestr)){
            likestr = "%"+likestr;
        }

        return likestr;
    }
    public static String getRightLike(String likestr) {
        if(StringUtil.isNotEmpty(likestr)){
            likestr = likestr+"%";
        }
        return likestr;
    }
}