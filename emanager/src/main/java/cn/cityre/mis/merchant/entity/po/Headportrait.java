package cn.cityre.mis.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class Headportrait implements java.io.Serializable {
      private Integer id;
      private String imagefile;
      private Integer flag;
      private String headtype;
      private String uid;
      private Date uploadtime;
      private String cocode;
      private String coname;
      private String imageURL;
}
