/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_trade_catalogEntity
 * @author zhengxiaotai
 * @version 2020-04-26
 */
@Table(name="nuanxin_trade_catalog", alias="a", columns={
		@Column(name="catalog_code", attrName="catalogCode", label="类目ID", isPK=true),
		@Column(name="catalog_name", attrName="catalogName", label="类目名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="catalog_type", attrName="catalogType", label="服务器类型"),
		@Column(name="catalog_display_type", attrName="catalogDisplayType", label="展示类型", isQuery=false),
		@Column(name="catalog_display_name", attrName="catalogDisplayName", label="展示标题", isQuery=false),
		@Column(name="money", attrName="money", label="基础金额"),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="status", attrName="status", label="status", isUpdate=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.tree_sorts, a.catalog_code"
)
public class NuanxinTradeCatalog extends TreeEntity<NuanxinTradeCatalog> {
	
	private static final long serialVersionUID = 1L;
	private String catalogCode;		// 类目ID
	private String catalogName;		// 类目名称
	private Integer catalogType;		// 服务器类型
	private Integer catalogDisplayType;		// 展示类型
	private String catalogDisplayName;		// 展示标题
	private Double money;		// 基础金额
	
	public NuanxinTradeCatalog() {
		this(null);
	}

	public NuanxinTradeCatalog(String id){
		super(id);
	}
	
	@Override
	public NuanxinTradeCatalog getParent() {
		return parent;
	}

	@Override
	public void setParent(NuanxinTradeCatalog parent) {
		this.parent = parent;
	}
	
	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	
	@NotBlank(message="类目名称不能为空")
	@Length(min=0, max=36, message="类目名称长度不能超过 36 个字符")
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	@NotNull(message="服务器类型不能为空")
	public Integer getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(Integer catalogType) {
		this.catalogType = catalogType;
	}
	
	public Integer getCatalogDisplayType() {
		return catalogDisplayType;
	}

	public void setCatalogDisplayType(Integer catalogDisplayType) {
		this.catalogDisplayType = catalogDisplayType;
	}
	
	@Length(min=0, max=20, message="展示标题长度不能超过 20 个字符")
	public String getCatalogDisplayName() {
		return catalogDisplayName;
	}

	public void setCatalogDisplayName(String catalogDisplayName) {
		this.catalogDisplayName = catalogDisplayName;
	}
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
}