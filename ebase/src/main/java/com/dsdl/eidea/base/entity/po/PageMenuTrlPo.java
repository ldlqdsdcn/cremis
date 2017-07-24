package com.dsdl.eidea.base.entity.po;

import com.dsdl.eidea.core.entity.po.MessagePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by 刘大磊 on 2017/1/12 13:24.
 */
@Entity
@Table(name = "sys_page_menu_trl", catalog = "cityre_mis")
@Getter
@Setter
public class PageMenuTrlPo {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "remark", length = 200)
    private String remark;
    @Column(name = "language_code", length = 10)
    private String languageCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_menu_id", nullable = false)
    private PageMenuPo pageMenuPo;
}
