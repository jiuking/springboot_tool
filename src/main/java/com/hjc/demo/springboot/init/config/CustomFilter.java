package com.hjc.demo.springboot.init.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component("customFilter")
public class CustomFilter extends FilterEventAdapter {

    public static final String DB_TYPE = "mysql";

    /*@Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        System.out.println("阿斯顿发顺丰大是大非");
        statementExecuteBefore(statement, statement.getSql());
        boolean firstResult = chain.preparedStatement_execute(statement);
        return firstResult;
    }*/

    @Override
    public int preparedStatement_executeUpdate(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        try {
            int result = super.preparedStatement_executeUpdate(chain, statement);
            String sql = statement.getSql();
            System.out.println("更新" + sql);
            sqlParserHandler(sql);
            System.out.println(statement.getParameters());
            Map<Integer, JdbcParameter> jdbcParameter = statement.getParameters();
            List<Object> list = new ArrayList<>();
            for (JdbcParameter pa : jdbcParameter.values()) {
                System.out.println(pa.getValue());
                list.add(pa.getValue());
            }

            String finalStr = transSql(list, sql);
            System.out.println(finalStr);
            return result;
        } catch (SQLException error) {
            statement_executeErrorAfter(statement, statement.getSql(), error);
            throw error;
        } catch (RuntimeException error) {
            statement_executeErrorAfter(statement, statement.getSql(), error);
            throw error;
        } catch (Error error) {
            statement_executeErrorAfter(statement, statement.getSql(), error);
            throw error;
        }
    }

    private void sqlParserHandler(String sql) {
        SQLStatement parser = SQLParserUtils.createSQLStatementParser(sql, DB_TYPE).parseStatement();
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        parser.accept(visitor);
        System.out.println(visitor.getTables());
    }

    private String transSql(List<Object> parameters, String sql) {

        if (sql.indexOf("?") < 0) {
            return sql;
        }

        for (int i = 0; i < parameters.size(); i++) {
            sql = sql.replaceFirst("\\?", parameters.get(i) != null ? "\'"
                    + parameters.get(i).toString() + "\'" : "NULL");
        }
        return sql;
    }

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        System.out.println("连接");
        super.connection_connectBefore(chain, info);
    }

    /*@Override
    protected void statementPrepareAfter(PreparedStatementProxy statement) {
        System.out.println("after");
    }


    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql) throws SQLException {
        System.out.println("sql语句12:"+sql);
        return super.connection_prepareStatement(chain, connection, sql);
    }

    @Override
    public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
        System.out.println("---->");
        return super.connection_connect(chain, info);
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        System.out.println("语句:::"+sql);
        super.statementExecuteAfter(statement, sql, result);
    }
*/
//    @Override
//    public void setStatementExecutableSqlLogEnable(boolean statementExecutableSqlLogEnable) {
//        super.setStatementExecutableSqlLogEnable(true);
//    }
}
