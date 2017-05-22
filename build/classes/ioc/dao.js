var ioc = {
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver',
            url: 'jdbc:sqlserver://127.0.0.1:1433; DatabaseName=scm_main',
            username: 'sa',
            password: '1234',
            maxActive: '100',
            minIdle: '30',
            initialSize: '2',
            defaultAutoCommit: 'false',
            testOnBorrow: "true",
            validationQuery: "select 1",
            filters: 'stat'
        }
    },
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        fields: {
            paths: ["app.properties"]
        }
    },
    dao: {
        type: 'org.nutz.dao.impl.NutDao',
        args: [{
            refer: "dataSource"
        }]
    }
};