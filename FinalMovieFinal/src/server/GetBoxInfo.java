package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datas.ConnectionManager;
import vos.MovieBoxInfo;

public class GetBoxInfo {
	public DailyBOList dbl;
	public ArrayList<MovieBoxInfo> mmlist;
	public ConnectionManager cm;

	// public static void main(String[] args) {
	// new GetBoxInfo();
	// }

	public GetBoxInfo() {
		try {
			dbl = new DailyBOList();
			dbl.fetchBoxList();
			mmlist = dbl.getMlist();

		} catch (Exception e) {
			e.printStackTrace();
		}
		maketable();
		setTable();
	}

	public void maketable() {
		Connection con = new ConnectionManager().getConnection();
		String sql = "";
		Statement st;
		try {
			con.setAutoCommit(false);

			sql = "drop table movieboxinfo";
			st = con.createStatement();
			st.executeUpdate(sql);
			sql = "create table movieboxinfo (moviecd varchar2(30), movienm varchar2(50), directornm varchar2(50), opendt varchar2(30), rank number(5), constraint movieb_pk primary key (moviecd))";
			st = con.createStatement();
			st.executeUpdate(sql);

			con.commit();
			con.setAutoCommit(true);

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		cm.close(con);

	}

	public void setTable() {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);

			for (int i = 0; i < mmlist.size(); i++) {
				String sql = "insert into movieboxinfo values(?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				String mc = mmlist.get(i).getMovieCd();
				String nm = mmlist.get(i).getMovieNm();
				String dr = mmlist.get(i).getDirector();
				String dt = mmlist.get(i).getOpenDt();
				ps.setString(1, mc);
				ps.setString(2, nm);
				ps.setString(3, dr);
				ps.setString(4, dt);
				ps.setInt(5, i + 1);
				ps.executeUpdate();
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		cm.close(con);
	}

	public ArrayList<MovieBoxInfo> getMmlist() {
		return mmlist;
	}

	public void setMmlist(ArrayList<MovieBoxInfo> mmlist) {
		this.mmlist = mmlist;
	}

}
