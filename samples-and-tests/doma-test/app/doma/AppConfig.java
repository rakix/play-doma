package doma;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;

import play.db.DB;
import play.modules.doma.PlayLogger;

/**
 * Doma用設定クラス
 */
public class AppConfig implements  Config  {

	/**
	 * データソース
	 */
	protected static final LocalTransactionDataSource   dataSource = createDataSource();

	/**
	 * SQL方言
	 */
	protected static final Dialect dialect = new H2Dialect();

	/**
	 * ロガー
	 */
	protected static final JdbcLogger jdbcLogger = new PlayLogger();

	/**
	 * データソースの取得
	 */
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * SQL方言の取得
	 */
	@Override
	public Dialect getDialect() {
		return dialect;
	}

	/**
	 * ロガーの取得
	 */
	@Override
	public JdbcLogger getJdbcLogger() {
		return jdbcLogger;
	}

	/**
	 * PlayのデータソースをDomaで使えるように設定
	 */
	protected static LocalTransactionDataSource createDataSource() {
		return new LocalTransactionDataSource(DB.getDataSource());
	}

	/**
	 * ローカルトランザクションの取得
	 */
	public static LocalTransaction getLocalTransaction() {
		return dataSource.getLocalTransaction(jdbcLogger);
	}
}