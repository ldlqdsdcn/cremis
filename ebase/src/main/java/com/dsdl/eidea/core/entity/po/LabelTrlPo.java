package com.dsdl.eidea.core.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * CoreLabelTrl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_label_trl", catalog = "cre_mis")
@Getter
@Setter
public class LabelTrlPo implements java.io.Serializable {

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang", nullable = false)
	private LanguagePo languagePo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_key", nullable = false)
	private LabelPo labelPo;
	@Column(name = "msgtext", nullable = false, length = 500)
	private String msgtext;
}