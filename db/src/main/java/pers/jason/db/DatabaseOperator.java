package pers.jason.db;

import org.apache.commons.dbutils.QueryRunner;

import javax.inject.Inject;

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
}
