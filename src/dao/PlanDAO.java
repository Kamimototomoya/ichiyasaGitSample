package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Plan;

public class PlanDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/daisan_insurance?characterEncoding=UTF-8&serverTimezone=JSTuseJDBCCompliantTimezoneShift"
			+ "=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String DB_USER = "root";
	private final String DB_PASS = "Tomo1014";

	/** PlanテーブルのSELECT文を実行するメソッド(商品一覧を表示) */
	public List<Plan> findByPlan() {

		List<Plan> planList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT PLAN_ID,NAME,PRICE FROM PLAN";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をPlanインスタンスに設定
			while (rs.next()) {
				int planId = rs.getInt("PLAN_ID");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");

				planList.add(new Plan(planId, name, price));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			return null; // DB接続に失敗した場合はnullを返す
		}
		return planList;

	}

}
