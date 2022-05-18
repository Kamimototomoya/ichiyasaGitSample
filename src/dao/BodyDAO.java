package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Detail;
import model.NewBody;

public class BodyDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_5c77a6069d3c34e?reconnect=true";
	private final String DB_USER = "bef9f359abaf81";
	private final String DB_PASS = "8982b1a2";

	/** bodyテーブルのSELECT文(注文詳細の閲覧)を実行するメソッド */
	public List<Detail> findByBody(int contractId) {

		List<Detail> detailList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT BODY.CONTRACT_ID,HEAD.CONTRACT_DATE,CUSTOMER.CUSTOMER_ID,CUSTOMER.NAME,PLAN.PLAN_ID,PLAN.NAME,PLAN.PRICE FROM BODY "
					+ "JOIN HEAD ON HEAD.CONTRACT_ID=BODY.CONTRACT_ID JOIN PLAN ON BODY.PLAN_ID=PLAN.PLAN_ID "
					+ "JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID=HEAD.CUSTOMER_ID WHERE HEAD.CONTRACT_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, contractId);

			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をBodyインスタンスに設定
			while (rs.next()) {
				contractId = rs.getInt("CONTRACT_ID");
				String contractDate = rs.getString("CONTRACT_DATE");
				int customerId = rs.getInt("CUSTOMER_ID");
				String customerName = rs.getString("CUSTOMER.NAME");
				int planId = rs.getInt("PLAN_ID");
				String planName = rs.getString("PLAN.NAME");
				int price = rs.getInt("PLAN.PRICE");

				detailList.add(new Detail(contractId, contractDate, customerId, customerName, planId, planName, price));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return detailList;

	}

	/** bodyテーブルに新規契約プランを登録するメソッド */
	public boolean create(int contractId, NewBody input) {

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO BODY(CONTRACT_ID,PLAN_ID)VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文中の「？」に使用する値を設定してSQLを完成
			pStmt.setInt(1, contractId);
			pStmt.setInt(2, input.getPlanId());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}

	/** 解約するメソッド */
	public boolean delete(int contractId) {

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// DELETE文の準備
			String sql = "DELETE FROM body WHERE contract_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// DELETE文中の「?」に使用する値を設定してSQLを完成
			pStmt.setInt(1, contractId);

			// DELETE文を実行
			int result = pStmt.executeUpdate();
			if (result < 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
