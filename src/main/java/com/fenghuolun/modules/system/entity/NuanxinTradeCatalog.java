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
 * @version 2020-04-07
 */
@Table(name="nuanxin_trade_catalog", alias="a", columns={
		@Column(name="catalog_code", attrName="catalogCode", label="catalog_code", isPK=true),
		@Column(name="catalog_name", attrName="catalogName", label="catalog_name", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="catalog_type", attrName="catalogType", label="catalog_type"),
		@Column(name="catalog_display_type", attrName="catalogDisplayType", label="catalog_display_type"),
		@Column(name="money", attrName="money", label="money"),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="status", attrName="status", label="status", isUpdate=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.tree_sorts, a.catalog_code"
)
public class NuanxinTradeCatalog extends TreeEntity<NuanxinTradeCatalog> {
	
	private static final long serialVersionUID = 1L;
	private String catalogCode;		// catalog_code
	private String catalogName;		// catalog_name
	private Integer catalogType;		// catalog_type
	private Integer catalogDisplayType;	// 展示类型
	private Double money;		// money
	
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
	
	@NotBlank(message="catalog_name不能为空")
	@Length(min=0, max=30, message="catalog_name长度不能超过 30 个字符")
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	@NotNull(message="catalog_type不能为空")
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
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
}