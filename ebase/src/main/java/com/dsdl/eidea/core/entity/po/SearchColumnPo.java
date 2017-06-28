package com.dsdl.eidea.core.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * CoreSearchColumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_search_column", catalog = "cre_mis")
@Setter
@Getter
public class SearchColumnPo implements java.io.Serializable {

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "core_search_id", nullable = false)
	private SearchPo coreSearch;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "core_label_key")
	private LabelPo coreLabel;
	@Column(name = "seq_no", nullable = false)
	private Integer seqNo;
	@Column(name = "name", length = 45)
	private String name;
	@Column(name = "property_name", length = 500)
	private String propertyName;
	@Column(name = "data_type")
	private Integer dataType;
	@Column(name = "show_type")
	private Integer showType;
	@Column(name = "rel_oper", length = 200)
	private String relOper;
	@Column(name = "fk_table", length = 50)
	private String fkTable;
	@Column(name = "fk_key_column", length = 45)
	private String fkKeyColumn;
	@Column(name = "fk_label_column", length = 45)
	private String fkLabelColumn;
	@Column(name = "coditions", length = 200)
	private String coditions;
	@Column(name = "remark", length = 200)
	private String remark;
	@Column(name = "newline", length = 1)
	private String newline;

}