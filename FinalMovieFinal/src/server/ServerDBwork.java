package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datas.ConnectionManager;
import datas.Data;
import server.api.Search;
import server.api.SearchBy;
import vos.UserComment;
import vos.MovieBoxInfo;
import vos.MovieSearchInfo;
import vos.User;

public class ServerDBwork {
	private ConnectionManager cm;
	private ServerGui gui;
	private ServerThread thth;
	private SearchBy sb;
	private Search sc;

	public ServerDBwork(ServerGui gui, ServerThread thth) {
		this.gui = gui;
		this.thth = thth;
		sb = new SearchBy();
		sc = new Search();
	}

	public int register(User u) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "insert into usertable values (?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getId());
			pst.setString(2, u.getPw());
			pst.setString(3, u.getMail());
			pst.setString(4, u.getPhN());
			pst.setString(5, u.getPic());
			int result = pst.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			cm.close(con);

			gui.setMessage(u.getId() + "유저가 가입하였습니다.");
			return Data.RG_SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
				con.setAutoCommit(true);
				cm.close(con);
			} catch (SQLException e1) {
			}
			e.printStackTrace();
			gui.setMessage("유저가 회원가입에 실패하였습니다.");
			return Data.FAIL;
		}
	}

	public User login(String id, String pw) {
		Connection con = new ConnectionManager().getConnection();
		User u = null;
		try {
			String sql = "select * from usertable where userid = ? and userpw = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				gui.setMessage(u.getId() + " 회원이 접속하였습니다.");
				thth.setUserID(u.getId());
			}

		} catch (Exception e) {

		}
		cm.close(con);
		return u;
	}

	public void changeIcon(User me, String iconUrl) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "update usertable set userpics=? where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, iconUrl);
			ps.setString(2, me.getId());
			ps.executeUpdate();

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

	public ArrayList<UserComment> getComment(String movieCD) {
		ArrayList<UserComment> cmList = new ArrayList<>();
		Connection con = new ConnectionManager().getConnection();
		String userIcon;

		try {
			String sql = "select e.userid, e.usertext, e.grade, e.moviecd, d.userpics, e.thumb, e.title from usercomment e join usertable d on e.userid=d.userid where moviecd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, movieCD);
			ResultSet rs = ps.executeQuery();
			UserComment c = null;
			while (rs.next()) {
				c = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				cmList.add(c);
				System.out.println("serverDB : " + c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.close(con);
		return cmList;
	}

	public ArrayList<UserComment> getMyComment(String id) {
		ArrayList<UserComment> cmList = new ArrayList<>();
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select e.userid, e.usertext, e.grade, e.moviecd, d.userpics, e.thumb, e.title from usercomment e join usertable d on e.userid=d.userid where e.userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			UserComment c = null;
			while (rs.next()) {
				c = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				cmList.add(c);
				System.out.println("serverDB : " + c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.close(con);
		return cmList;
	}

	///////////////////////////////////
	public int amiAlreadyWrote(UserComment c) {
		Connection con = new ConnectionManager().getConnection();
		int count = Data.NOCOMMENT;
		try {
			String sql = "select * from usercomment where userid=? and moviecd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getUserID());
			ps.setString(2, c.getMovieCD());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.close(con);
		return count;
	}

	public boolean writeComment(UserComment c) {
		int count = amiAlreadyWrote(c);
		Connection con = new ConnectionManager().getConnection();
		if (count == Data.NOCOMMENT) {
			try {
				con.setAutoCommit(false);
				String sql = "insert into usercomment values(?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, c.getUserID());
				ps.setString(2, c.getMovieCD());
				ps.setString(3, c.getUserText());
				ps.setDouble(4, c.getGrade());
				ps.setString(5, c.getUserPic());
				ps.setString(6, c.getThumb());
				ps.setString(7, c.getTitle());
				ps.executeUpdate();

				con.commit();
				con.setAutoCommit(true);
				cm.close(con);
				return true;

			} catch (Exception e) {
				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} else {
			try {
				con.setAutoCommit(false);
				String sql = "update usercomment set usertext=?, grade=? where userid=? and moviecd=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, c.getUserText());
				ps.setDouble(2, c.getGrade());
				ps.setString(3, c.getUserID());
				ps.setString(4, c.getMovieCD());
				ps.executeUpdate();

				con.commit();
				con.setAutoCommit(true);
				cm.close(con);
				return true;

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
		}
		cm.close(con);
		return false;
	}

	public void delComment(String id, String moviecd) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "delete from usercomment where userid=? and moviecd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, moviecd);

			ps.executeUpdate();

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
	///////////////////////////// ./

	public void saveUserLike(String id, MovieSearchInfo msi) {
		int isIt = getUserLikebyCD(id, msi.getMvCode());
		Connection con = new ConnectionManager().getConnection();

		if (isIt == Data.USERLIKETHIS) {
			try {
				con.setAutoCommit(false);
				String sql = "delete userlike where userid=? and moviecd=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, msi.getMvCode());
				ps.executeUpdate();

				con.commit();
				con.setAutoCommit(true);

			} catch (SQLException e) {

				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		} else {

			try {
				con.setAutoCommit(false);
				String sql = "insert into userlike values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, msi.getMvCode());
				ps.setString(3, msi.getMvTitle());
				ps.setString(4, msi.getMvGenre());
				ps.setString(5, msi.getMvDirector());
				ps.setString(6, msi.getMvActor());
				ps.setString(7, msi.getMvThumb());
				ps.setString(8, msi.getMvStory());
				ps.setString(9, msi.getMvTm());
				ps.executeUpdate();
				con.commit();

				con.setAutoCommit(true);
				cm.close(con);

			} catch (Exception e) {
				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		cm.close(con);
	}

	public int getUserLikebyCD(String id, String mvCd) {
		int result = -1;
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select userid, moviecd from userlike where userid=? and moviecd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, mvCd);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = Data.USERLIKETHIS;
			}
			cm.close(con);

		} catch (Exception e) {
			result = Data.FAIL;
			e.printStackTrace();
		}
		cm.close(con);
		return result;
	}

	public ArrayList<MovieSearchInfo> getUserLikebyID(String id) {
		ArrayList<MovieSearchInfo> sList = new ArrayList<>();

		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select moviecd, title, genre, director, actor, thumb, story, mytm from userlike where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			MovieSearchInfo m = null;
			while (rs.next()) {
				m = new MovieSearchInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				sList.add(m);
			}
			cm.close(con);

		} catch (Exception e) {
			e.printStackTrace();

		}
		cm.close(con);

		return sList;
	}

	public Double getMovieAvgGrade(String movieCd) {
		double grade = (double) Data.FAIL;
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select moviecd, avg(grade) gg from usercomment where moviecd=? group by (moviecd)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, movieCd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				grade = rs.getDouble(2);
			}
		} catch (Exception e) {

		}
		cm.close(con);
		return grade;
	}

	public ArrayList<MovieSearchInfo> searchAPI(int ccase, String que) {
		ArrayList<MovieSearchInfo> seList = new ArrayList<MovieSearchInfo>();
		try {
			sb.Search(ccase, que);
			seList = sb.getSearchList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(seList);
		return seList;

	}

	public String searchYouTube(String keyWord) {
		String mvc = sc.SearchUWant(keyWord);
		String ur = "https://www.youtube.com/v/" + mvc + "?version=3&enablejsapi=1&playerapiid=ytplayer";
		return ur;
	}

	public ArrayList<Integer> getNums(String id) {
		ArrayList<Integer> numList = new ArrayList<>();
		Connection con = new ConnectionManager().getConnection();
		try {
			// String sql = "select count(userid) from usercomment where
			// userid=? union select count(userid) from userlike where
			// userid=?";
			String sql = "select (select count(userid) from userlike where userid=?), (select count(userid) from usercomment where userid=?) from dual";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				numList.add(rs.getInt(1));
				numList.add(rs.getInt(2));
			}
		} catch (Exception e) {

		}
		cm.close(con);

		return numList;
	}

	public UserComment getBestM() { // 최고평점가진 usercomment 가져오기
		Connection con = new ConnectionManager().getConnection();
		UserComment uc = null;
		ArrayList<UserComment> ucList = new ArrayList<>();
		try {
			String sql = "select * from usercomment where moviecd = (select moviecd from usercomment group by moviecd having avg(grade) = (select max(avg(grade)) from usercomment group by moviecd))";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				uc = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(4), rs.getString(3),
						rs.getString(5), /// !!!!!!!
						rs.getString(6), rs.getString(7));
				ucList.add(uc);
			}
			// cm.close(con);
			if (ucList.size() == 0) {
				return null;
			} else {
				return ucList.get(0);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("ServerDBWork:464");
			try {
				String sql = "select * from usercomment where moviecd = (select moviecd from usercomment group by moviecd, rownum having avg(grade) = (select max(avg(grade)) from usercomment group by moviecd) and rownum = 1)";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					uc = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(4), rs.getString(3),
							rs.getString(5), /// !!!!!!!
							rs.getString(6), rs.getString(7));
					ucList.add(uc);
				}
				// cm.close(con);
				if (ucList.size() == 0) {
					return null;
				} else {
					return ucList.get(0);
				}
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		cm.close(con);
		return ucList.get(0);
	}

	public UserComment getBestC() {
		Connection con = new ConnectionManager().getConnection();
		UserComment uc = null;
		ArrayList<UserComment> ucList = new ArrayList<>();
		try {
			String sql = "select title, thumb, count(userid) count from usercomment group by thumb, title order by count desc";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				uc = new UserComment("", "", 0.0, "", "", /// !!!!!!!
						rs.getString(2), rs.getString(1));
				ucList.add(uc);

				// cm.close(con);

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (ucList.size() == 0) {
				return null;
			} else {
				return ucList.get(0);
			}

		}
		cm.close(con);
		return ucList.get(0);
	}

	public UserComment getBestL() {
		Connection con = new ConnectionManager().getConnection();
		UserComment uc = null;
		ArrayList<UserComment> ucList = new ArrayList<>();
		try {
			String sql = "select title, thumb, count(userid) count from userlike group by thumb, title order by count desc";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				uc = new UserComment("", "", 0.0, "", "", /// !!!!!!!
						rs.getString(2), rs.getString(1));
				ucList.add(uc);
			}
			// cm.close(con);
			if (ucList.size() == 0) {
				return null;
			} else {
				return ucList.get(0);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("ServerDBWork:558");
			try {
				String sql = "select * from usercomment where moviecd = (select moviecd from userlike group by moviecd, rownum having count(userid) = (select max(count(userid)) from userlike group by moviecd) and rownum = 1)";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					uc = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(4), rs.getString(3),
							rs.getString(5), /// !!!!!!!
							rs.getString(6), rs.getString(7));
					ucList.add(uc);
				}
				// cm.close(con);
				if (ucList.size() == 0) {
					return null;
				} else {
					return ucList.get(0);
				}
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		cm.close(con);
		return ucList.get(0);
	}
}
