package pers.jason.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.inject.Inject;

import java.sql.SQLException;

import static java.util.Objects.requireNonNull;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 16:21
 */
public class DatabaseOperator {

  private final QueryRunner queryRunner;

  @Inject
  public DatabaseOperator(QueryRunner queryRunner) {
    requireNonNull(queryRunner.getDataSource(), "dataSource can not be null.");
    this.queryRunner = queryRunner;
  }

  /**
   * Executes the given SELECT SQL operations.
   * @param baseQuery The SQL query statement to execute.
   * @param resultHandler The handler used to create the result object
   * @param params Initialize the PreparedStatement's IN parameters
   * @param <T> The type of object that the qeury handler returns
   * @return The object returned by the handler.
   * @throws SQLException
   */
  public <T> T query(final String baseQuery, final ResultSetHandler<T> resultHandler,
                     final Object... params) throws SQLException {
    return this.queryRunner.query(baseQuery, resultHandler, params);
  }

  /**
   * Executes the given a INSERT, UPDATE, or DELETE SQL statement.
   * @param updateClause sql statements to execute
   * @param params Initialize the PreparedStatement's IN parameters
   * @return The number of rows updated.
   * @throws SQLException
   */
  public int update(final String updateClause, final Object... params) throws SQLException {
    return this.queryRunner.update(updateClause, params);
  }

  /**
   * Execute a batch operation
   * @param sqlCommand sqlCommand template
   * @param params parameters
   * @return
   * @throws SQLException
   */
  public int[] batch(final String sqlCommand, final Object[]... params) throws SQLException {
    return this.queryRunner.batch(sqlCommand, params);
  }

}
