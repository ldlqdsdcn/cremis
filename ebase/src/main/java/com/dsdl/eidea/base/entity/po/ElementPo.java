package com.dsdl.eidea.base.entity.po;

import com.dsdl.eidea.base.init.InputTypeConverter;
import com.dsdl.eidea.core.def.FieldInputType;
import com.dsdl.eidea.core.def.FieldShowType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by 车东明 on 2017/5/27.
 */
@Getter
@Setter
@Entity
@Table(name = "core_element",catalog = "cre_mis")
public class ElementPo {
    @Id
    @Column(name = "id",nullable = false,unique = true,length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",length = 200)
    @Length(min=2,max = 200,message = "error.datadicttype.name.length")
    private String name;
    @Column(name = "element_input_type",length = 11)
    @Convert(converter = InputTypeConverter.class)
    private FieldInputType elementInputType;
    @Column(name = "width",length = 11)
    private Integer width;
    @Column(name = "height",length = 11)
    private Integer height;
    @Column(name = "description",length = 255)
    @Length(max = 255,message="error.element.description.length")
    private String description;
    @Column(name = "element_show_type",length = 11)
    private FieldShowType elementShowType;


}
