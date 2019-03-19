package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class Bean {

	private String account;
	private String password;
	private String e_mail;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public Bean(String account, String password, String e_mail) {

		this.account = account;
		this.password = password;
		this.e_mail = e_mail;
	}
	public void totalConnection(String sql) {
		try {
			cn = DBConnection.getDB().ConnectionDB();
			st = cn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	// 登入
	public String login() {
		String loginmsg = "";
		try {
			int count = 0;
			String getpass = "select password from user where account='" + this.account + "';";
			totalConnection(getpass);
			while (rs.next()) {
				count = 1;
				if (rs.getString(1).equals(this.password)) {
					loginmsg = "登入成功";
				} else {
					loginmsg = "登入失敗";
				}
				if (count == 0) {
					loginmsg = "無此使用者";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getDB().close(rs, st, cn);
		}
		return loginmsg;
	}

	// 註冊
	@SuppressWarnings("unused")
	public String enrollDBInsert() {
		String enrollmsg = "";
		int accCount = 0;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		if (this.account != null && !this.account.equals("") && this.password != null && !this.password.equals("")
				&& this.e_mail != null && !this.e_mail.equals("")) {
			try {
				String enroll="select count(account) from user where account='" + account + "'";
				cn = DBConnection.getDB().ConnectionDB();
				st = cn.createStatement();
				rs = st.executeQuery(enroll);
				if (rs!=null) {
					while (rs.next()) {
						accCount = rs.getInt(1);
					}
				}
				
				if (accCount == 0) {
					String insertSql = "insert into user(account,password,e_mail)values('" + this.account + "','"
							+ this.password + "','" + this.e_mail + "')";
					st.execute(insertSql);
					enrollmsg = "建立成功";
				} else {
					enrollmsg = "建立失敗";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.getDB().close(rs, st, cn);
			}

		}

		return enrollmsg;
	}

	// 修改密碼
	public String changePassword( String account, String pass, String pass2, String e_mail) {
		String changemsg = "fail";
		try {
			int count = 0;
			String getpass = "select password,e_mail,account from user where account='" + account + "'";
			cn = DBConnection.getDB().ConnectionDB();
			st = cn.createStatement();
			rs = st.executeQuery(getpass);
			while (rs.next()) {
				count = 1;
				if (rs.getString(1).equals(pass) && rs.getString(2).equals(e_mail)&&rs.getString(3).equals(account)) {
					String changeSql = "Update user Set password='" + pass2 + "' where account='" + account + "'";
					st.execute(changeSql);

					changemsg = "修改密碼成功";
					break;
				} else {
					changemsg = "修改密碼失敗";
				}
				if (count == 0) {
					changemsg = "無此使用者";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getDB().close(rs, st, cn);
		}
		return changemsg;
	}

	// 查詢密碼
	public String[] getInformation(String account, String e_mail) {
		String[] msg = new String[2];
		try {
			int count = 0;
			String getpass = "select count(password),password from user where account='" + account + "' and e_mail='" + e_mail
					+ "'";
			cn = DBConnection.getDB().ConnectionDB();
			st = cn.createStatement();
			rs = st.executeQuery(getpass);
			while (rs.next()) {
				count = 1;
				if (rs.getInt(1)>0 ) {
					msg[0] = "查詢成功";
					if(msg[0].equals("查詢成功")) {
						msg[1]=rs.getString(2);
					 }
				} else if (rs.getString(0).equals(account) && !rs.getString(2).equals(e_mail)) {
					msg[0] = "E-mail輸入錯誤";
				} else {
					msg[0] = "帳號輸入錯誤";
				}
				if (count == 0) {
					msg[0] = "無此帳號";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return msg;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

}