package com.hjc.demo.springboot.init.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("customFilter")
public class CustomFilter extends Slf4jLogFilter {
    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        System.out.println("阿斯顿发顺丰大是大非");
        statementExecuteBefore(statement, statement.getSql());
        boolean firstResult = chain.preparedStatement_execute(statement);
        return firstResult;
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        System.out.println("语句:::"+sql);
        setStatementExecutableSqlLogEnable(true);
        super.statementExecuteAfter(statement, sql, result);
    }
}
