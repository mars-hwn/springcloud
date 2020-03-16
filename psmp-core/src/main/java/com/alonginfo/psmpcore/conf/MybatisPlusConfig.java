package com.alonginfo.psmpcore.conf;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

	/** * mybatis-plus分页插件 */
	@Bean public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
//		page.setDialectType("mysql"); //分页时的总计条数
		return page;
	}
}