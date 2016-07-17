package org.groovy.cookbook.dbunit;

import groovy.xml.StreamingMarkupBuilder
import org.dbunit.JdbcDatabaseTester
import groovy.sql.Sql
import org.dbunit.*
import org.dbunit.dataset.xml.*
import org.dbunit.database.*

class DbUnitSampleTest extends GroovyTestCase {

    static {
        Sql.newInstance('jdbc:hsqldb:db/sample', 'sa', '', 'org.hsqldb.jdbcDriver').execute """
            CREATE TABLE emp (
                empno INTEGER PRIMARY KEY,
                ename VARCHAR(10),
                job VARCHAR(9)
            );"""
    }

    def tester

    void setUp() {
        tester = new JdbcDatabaseTester('org.hsqldb.jdbcDriver', 'jdbc:hsqldb:db/sample', 'sa', '')
    }

    void testDelete() {
        tester.dataSet = dataSet {
            emp empno:7369, ename:'SMITH', job:'CLERK'
            emp empno:7499, ename:'ALLEN', job:'SALESMAN'
            emp empno:7521, ename:'WARD', job:'SALESMAN'
        }
        tester.onSetup()

        assert 1 == Sql.newInstance('jdbc:hsqldb:db/sample', 'sa', '', 'org.hsqldb.jdbcDriver')
            .executeUpdate('delete from emp where empno = 7499')

        Assertion.assertEquals dataSet {
            emp empno:7369, ename:'SMITH', job:'CLERK'
            emp empno:7521, ename:'WARD', job:'SALESMAN'
        }, tester.connection.createDataSet()
    }

    def dataSet(c) {
        new FlatXmlDataSetBuilder().build(new File("expectedDataSet.xml"));        
        //new FlatXmlDataSet(new StringReader(new StreamingMarkupBuilder().bind{dataset c}.toString()))
    }
}