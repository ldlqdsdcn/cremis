package com.dsdl.eidea.core.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CoreLanguage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_language", catalog = "cityre_mis")
@Setter
@Getter
public class LanguagePo implements java.io.Serializable {

    // Fields
    @Id
    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;
    @Column(name = "name", unique = true, nullable = false, length = 200)
    private String name;
    @Column(name = "remark", length = 500)
    private String remark;
    @Column(name = "isactive", nullable = false, length = 1)
    private String isactive;
    @Column(name = "language_iso",nullable = false,length = 2)
    private String languageIso;
    @Column(name = "country_code",nullable = false,length = 2)
    private String countryCode;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languageByLanguageCode")
    private List<LanguageTrlPo> languageTrlsForLanguageCode = new ArrayList<>(
            0);
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "languagePo")
    private Set<MessageTrlPo> messageTrls = new HashSet<MessageTrlPo>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languageByLang")
    private Set<LanguageTrlPo> languageTrlsForLang = new HashSet<LanguageTrlPo>(
            0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languagePo")
    private Set<LabelTrlPo> labelTrls = new HashSet<LabelTrlPo>(0);
}