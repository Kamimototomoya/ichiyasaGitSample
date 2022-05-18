package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.Login;
import model.NewEmployee;

public class EmployeeDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "mysql://beeef9d29206b1:caebb262@us-cdbr-east-05.cleardb.net/heroku_a8a464ed316f369?reconnect=true";
	private final String DB_USER = "beeef9d29206b1";
	private final String DB_PASS = "caebb262";

	/** customerテーブルのSELECT文(アカウントの照合)を実行するメソッド */
	public Employee findByEmployee(Login input) {

		Employee employee = null;

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT EMPLOYEE_ID,NAME FROM EMPLOYEE WHERE EMPLOYEE_ID=? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, input.getEmployeeId());
			pStmt.setString(2, input.getPass());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をEMPLOYEEインスタンスに設定
			while (rs.next()) {
				String employeeId = rs.getString("EMPLOYEE_ID");
				String name = rs.getString("NAME");
				employee = new Employee(employeeId, name);
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーまたはnullを返す
		return employee;
	}

	public boolean create(NewEmployee input) {

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO EMPLOYEE(EMPLOYEE_ID,NAME,PASS)VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文中の「？」に使用する値を設定してSQLを完成
			pStmt.setString(1, input.getEmployeeId());
			pStmt.setString(2, input.getName());
			pStmt.setString(3, input.getPass());

			// INSERT文を実行
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
