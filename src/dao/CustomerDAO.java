package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.NewCustomer;

public class CustomerDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/daisan_insurance?characterEncoding=UTF-8&serverTimezone=JSTuseJDBCCompliantTimezoneShift"
			+ "=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String DB_USER = "root";
	private final String DB_PASS = "Tomo1014";

	/** customerテーブルのSELECT文(顧客一覧の閲覧)を実行するメソッド */
	public List<Customer> findByCustomer(int customerId) {

		List<Customer> customerList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT CUSTOMER_ID,NAME,REGISTRATION_DATE,AGE,ADDRESS,TEL FROM CUSTOMER WHERE CUSTOMER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, customerId);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をインスタンスに設定
			while (rs.next()) {
				String name = rs.getString("NAME");
				String registrationDate = rs.getString("REGISTRATION_DATE");
				int age = rs.getInt("AGE");
				String address = rs.getString("ADDRESS");
				String tel = rs.getString("TEL");

				customerList.add(new Customer(customerId, name, registrationDate, age, address, tel));
			}

		} catch (SQLException e) {
			return null;
		}

		return customerList;
	}

	/** customerテーブルのSELECT文(顧客一覧の閲覧)を実行するメソッド */
	public List<Customer> findByCustomer() {

		List<Customer> customerList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT CUSTOMER_ID,NAME,REGISTRATION_DATE,AGE,ADDRESS,TEL FROM CUSTOMER";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をインスタンスに設定
			while (rs.next()) {
				int customerId = rs.getInt("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String registrationDate = rs.getString("REGISTRATION_DATE");
				int age = rs.getInt("AGE");
				String address = rs.getString("ADDRESS");
				String tel = rs.getString("TEL");

				customerList.add(new Customer(customerId, name, registrationDate, age, address, tel));
			}

		} catch (SQLException e) {
			return null;
		}

		return customerList;
	}

	/** customerテーブルのINSERT文(顧客一覧の登録)を実行するメソッド */
	public int create(NewCustomer data) {

		int customerId = 0;

		// データベースの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO CUSTOMER(NAME,REGISTRATION_DATE,AGE,ADDRESS,TEL) VALUES(?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// INSERT文中の「？」に使用する値を設定してSQLを完成
			pStmt.setString(1, data.getName());
			pStmt.setString(2, data.getRegistrationDate());
			pStmt.setInt(3, data.getAge());
			pStmt.setString(4, data.getAddress());
			pStmt.setString(5, data.getTel());

			// INSERT文を実行
			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				customerId = rs.getInt(1);
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			return customerId;
		}
		return customerId;
	}

}
