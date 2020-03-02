/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_carouselEntity
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Table(name="nuanxin_carousel", alias="a", columns={
		@Column(name="carousel_id", attrName="carouselId", label="ID", isPK=true),
		@Column(name="carousel_name", attrName="carouselName", label="轮播图名称", queryType=QueryType.LIKE),
		@Column(name="carousel_image", attrName="carouselImage", label="轮播图", isQuery=false),
		@Column(name="carousel_link", attrName="carouselLink", label="轮播图链接", isQuery=false),
		@Column(name="carousel_detail", attrName="carouselDetail", label="carousel_detail", isQuery=false),
		@Column(name="carousel_type", attrName="carouselType", label="carousel_type"),
		@Column(name="update_time", attrName="updateTime", label="更新时间", isQuery=false),
	}, orderBy="a.carousel_id DESC"
)
public class NuanxinCarousel extends DataEntity<NuanxinCarousel> {
	
	private static final long serialVersionUID = 1L;
	private Integer carouselId;		// ID
	private String carouselName;		// 轮播图名称
	private String carouselImage;		// 轮播图
	private String carouselLink;		// 轮播图链接
	private String carouselDetail;		// carousel_detail
	private Integer carouselType;		// carousel_type
	private Date updateTime;		// 更新时间
	
	public NuanxinCarousel() {
		this(null);
	}

	public NuanxinCarousel(String id){
		super(id);
	}
	
	public Integer getCarouselId() {
		return carouselId;
	}

	public void setCarouselId(Integer carouselId) {
		this.carouselId = carouselId;
	}
	
	@NotBlank(message="轮播图名称不能为空")
	@Length(min=0, max=100, message="轮播图名称长度不能超过 100 个字符")
	public String getCarouselName() {
		return carouselName;
	}

	public void setCarouselName(String carouselName) {
		this.carouselName = carouselName;
	}
	
	@NotBlank(message="轮播图不能为空")
	@Length(min=0, max=200, message="轮播图长度不能超过 200 个字符")
	public String getCarouselImage() {
		return carouselImage;
	}

	public void setCarouselImage(String carouselImage) {
		this.carouselImage = carouselImage;
	}
	
	@Length(min=0, max=200, message="轮播图链接长度不能超过 200 个字符")
	public String getCarouselLink() {
		return carouselLink;
	}

	public void setCarouselLink(String carouselLink) {
		this.carouselLink = carouselLink;
	}
	
	@Length(min=0, max=200, message="carousel_detail长度不能超过 200 个字符")
	public String getCarouselDetail() {
		return carouselDetail;
	}

	public void setCarouselDetail(String carouselDetail) {
		this.carouselDetail = carouselDetail;
	}
	
	@NotNull(message="carousel_type不能为空")
	public Integer getCarouselType() {
		return carouselType;
	}

	public void setCarouselType(Integer carouselType) {
		this.carouselType = carouselType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}