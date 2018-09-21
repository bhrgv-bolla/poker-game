package org.bbolla.pokergame.fivecard;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    /**
     *
     dataSource.cachePrepStmts=true
     dataSource.prepStmtCacheSize=250
     dataSource.prepStmtCacheSqlLimit=2048
     dataSource.useServerPrepStmts=true
     dataSource.useLocalSessionState=true
     dataSource.rewriteBatchedStatements=true
     dataSource.cacheResultSetMetadata=true
     dataSource.cacheServerConfiguration=true
     dataSource.elideSetAutoCommits=true
     dataSource.maintainTimeStats=false


     * @return
     */
    @Bean
    public DataSource dataSource() {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/poker");
        dataSource.setUseServerPrepStmts(true);
        dataSource.setCachePreparedStatements(true);
        dataSource.setRewriteBatchedStatements(true);
        dataSource.setDontCheckOnDuplicateKeyUpdateInSQL(true);
        return dataSource;
    }
}
