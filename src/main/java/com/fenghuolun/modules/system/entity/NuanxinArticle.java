/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_articleEntity
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Table(name="nuanxin_article", alias="a", columns={
		@Column(name="article_id", attrName="articleId", label="ID", isPK=true),
		@Column(name="article_name", attrName="articleName", label="文章标题", queryType=QueryType.LIKE),
		@Column(name="article_image", attrName="articleImage", label="文章封面图", isQuery=false),
		@Column(name="article_link", attrName="articleLink", label="文章链接", isQuery=false),
		@Column(name="update_time", attrName="updateTime", label="更新时间", isQuery=false),
	}, orderBy="a.article_id DESC"
)
public class NuanxinArticle extends DataEntity<NuanxinArticle> {
	
	private static final long serialVersionUID = 1L;
	private String articleId;		// ID
	private String articleName;		// 文章标题
	private String articleImage;		// 文章封面图
	private String articleLink;		// 文章链接
	private Date updateTime;		// 更新时间
	
	public NuanxinArticle() {
		this(null);
	}

	public NuanxinArticle(String id){
		super(id);
	}
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	@NotBlank(message="文章标题不能为空")
	@Length(min=0, max=100, message="文章标题长度不能超过 100 个字符")
	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	@NotBlank(message="文章封面图不能为空")
	@Length(min=0, max=200, message="文章封面图长度不能超过 200 个字符")
	public String getArticleImage() {
		return articleImage;
	}

	public void setArticleImage(String articleImage) {
		this.articleImage = articleImage;
	}
	
	@Length(min=0, max=200, message="文章链接长度不能超过 200 个字符")
	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}