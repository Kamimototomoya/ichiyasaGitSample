package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Condition;
import model.NewHead;
import model.Result;

public class HeadDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/daisan_insurance?characterEncoding=UTF-8&serverTimezone=JSTuseJDBCCompliantTimezoneShift"
			+ "=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String DB_USER = "root";
	private final String DB_PASS = "Tomo1014";

	/** 絞り込み結果を表示するメソッド */
	public List<Result> findByHead(Condition input) {
		List<Result> resultList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String start = input.getStart();
			String end = input.getEnd();
			String customer = input.getCustomerName();
			String plan = input.getPlanName();
			String employeeId = input.getEmployeeId();

			List<String> conditionList = new ArrayList<>();

			if (!start.equals("")) {
				conditionList.add("CONTRACT_DATE>='" + start + "' ");
			}
			if (!end.equals("")) {
				conditionList.add("CONTRACT_DATE<'" + end + "' ");
			}
			if (!customer.equals("")) {
				conditionList.add("CUSTOMER.NAME LIKE '%" + customer + "%' ");
			}
			if (!plan.equals("")) {
				conditionList.add("PLAN.NAME LIKE '%" + plan + "%' ");
			}
			conditionList.add("HEAD.EMPLOYEE_ID='" + employeeId + "' ");

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < conditionList.size(); i++) {
				if (i == 0) {
					sb.append(" WHERE ");
				} else if (i != 0) {
					sb.append(" AND ");
				}
				sb.append(conditionList.get(i));
			}

			String sql = "SELECT HEAD.CONTRACT_ID,CONTRACT_DATE,CUSTOMER.NAME,SUM(PLAN.PRICE) AS TOTAL FROM HEAD "
					+ "JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID=HEAD.CUSTOMER_ID JOIN BODY "
					+ "ON BODY.CONTRACT_ID=HEAD.CONTRACT_ID JOIN PLAN ON PLAN.PLAN_ID=BODY.PLAN_ID " + sb.toString()
					+ "GROUP BY HEAD.CONTRACT_ID ORDER BY CONTRACT_DATE";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をresultインスタンスに設定
			while (rs.next()) {
				int contractId = rs.getInt("CONTRACT_ID");
				String contractDate = rs.getString("CONTRACT_DATE");
				String customerName = rs.getString("CUSTOMER.NAME");
				int total = rs.getInt("TOTAL");

				resultList.add(new Result(contractId, contractDate, customerName, total));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
		return resultList;
	}

	/** 新規契約を登録するメソッド */
	public int create(NewHead input) {

		int contractId = 0;

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO HEAD(CONTRACT_DATE,CUSTOMER_ID,EMPLOYEE_ID)VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// INSERT文中の「?」に使用する値を設定してSQLを完成
			pStmt.setString(1, input.getDate());
			pStmt.setInt(2, input.getCustomerId());
			pStmt.setString(3, input.getEmployeeId());

			// INSERT文を実行
			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				contractId = rs.getInt(1);
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			return contractId;
		}

		return contractId;
	}

	/** 解約するメソッド */
	public boolean delete(int contractId) {

//		int contractId = 0;

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// DELETE文の準備
			String sql = "DELETE FROM head WHERE contract_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// DELETE文中の「?」に使用する値を設定してSQLを完成
			pStmt.setInt(1,contractId);

			// DELETE文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
