package com.gpdi.hqplus.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.gpdi.hqplus.common.entity.UserContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-06-13 15:10
 **/
@Configuration
public class MybatisPlusConfig {
    /**
     * 多租户字段
     */
    private static final String SYSTEM_TENANT_ID = "project_code";
    /**
     * 忽略的表
     */
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList(
            "tb_user",
            "tb_user_extend",
            "tb_project",
            "tb_file_resource"
    );

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /*
    一阶段暂时不上多租户功能

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        // SQL解析处理拦截：增加租户处理回调。
        TenantSqlParser tenantSqlParser = new TenantSqlParser()
                .setTenantHandler(new TenantHandler() {
                    @Override
                    public Expression getTenantId() {
                        // 从当前系统上下文中取出当前请求的多租户ID，通过解析器注入到SQL中。
                        if (UserContext.get() == null) {
                            return new StringValue("");
                        }
                        String parkCode = UserContext.get().getProjectCode();
                        return new StringValue(parkCode);
                    }

                    @Override
                    public String getTenantIdColumn() {
                        // 多租户字段
                        return SYSTEM_TENANT_ID;
                    }

                    @Override
                    public boolean doTableFilter(String tableName) {
                        // 忽略表
                        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
                    }
                });
        paginationInterceptor.setSqlParserList(Lists.newArrayList(tenantSqlParser));
        return paginationInterceptor;
    }

    @Bean(name = "performanceInterceptor")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
     */
}
