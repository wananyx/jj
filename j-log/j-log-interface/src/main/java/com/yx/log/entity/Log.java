package com.yx.log.entity;

import com.yx.common.vo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 日志对象
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log extends BaseEntity {

	private Long id;
	/** 用户名 */
	private String username;
	/** 模块 */
	private String module;
	/** 参数值 */
	private String params;
	/** 备注 */
	private String remark;
	/** 是否执行成功 */
	private Boolean flag;
	private Date created;
}
