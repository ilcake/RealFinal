package server;

import java.io.BufferedInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import vos.MovieBoxInfo;

public class DailyBOList {///// 변화!

	private ArrayList<String> titleList;
	private ArrayList<String> dList;
	private ArrayList<String> dateList;
	private ArrayList<String> codeList;
	private ArrayList<MovieBoxInfo> mlist;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new DailyBOList();
		} catch (Exception e) {
			// TODO Auto-generated catch block//
			e.printStackTrace();
		}
	}

	public DailyBOList() {
		try {
			fetchBoxList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fetchBoxList() throws Exception { // json parsing
		titleList = new ArrayList<String>();
		dList = new ArrayList<String>();
		dateList = new ArrayList<String>();
		codeList = new ArrayList<String>();
		// 전일대비순위증감 넣을까 말까//

		JSONParser jsonparser = new JSONParser(); // 영화제목
		JSONObject jsonobject = (JSONObject) jsonparser.parse(readUrl());
		JSONObject json = (JSONObject) jsonobject.get("boxOfficeResult");
		JSONArray array = (JSONArray) json.get("dailyBoxOfficeList");
		for (int i = 0; i < array.size(); i++) {
			JSONObject entity = (JSONObject) array.get(i);
			String movieNm = (String) entity.get("movieNm");
			titleList.add(movieNm);
		}
		for (int i = 0; i < array.size(); i++) {
			JSONObject entity = (JSONObject) array.get(i);
			String movieCd = (String) entity.get("movieCd");
			codeList.add(movieCd);
		}

		for (int i = 0; i < array.size(); i++) {
			JSONObject entity = (JSONObject) array.get(i);
			String openDt = (String) entity.get("openDt");
			dateList.add(openDt);
		}

		///////////////////////////////////////////////////////// 영화코드로 감독받기
		for (Object object : codeList) {
			JSONParser jsonparser2 = new JSONParser();
			JSONObject jsonobject2 = (JSONObject) jsonparser2.parse(readInfoUrl((String) object));
			JSONObject json1 = (JSONObject) jsonobject2.get("movieInfoResult");
			JSONObject json2 = (JSONObject) json1.get("movieInfo");
			JSONArray array2 = (JSONArray) json2.get("directors");

			// for (int i = 0; i < array2.size(); i++) {
			JSONObject entity = (JSONObject) array2.get(0);
			String director = (String) entity.get("peopleNm");
			dList.add(director);
			// }
		}

		// System.out.println(titleList.toString());
		// System.out.println(codeList.toString());
		// System.out.println(dateList.toString());
		// System.out.println(dList.toString());

		mlist = new ArrayList<MovieBoxInfo>();

		for (int i = 0; i < 10; i++) {
			mlist.add(new MovieBoxInfo(titleList.get(i), codeList.get(i), dList.get(i), dateList.get(i)));
		}
		// for (MovieBoxInfo mm : mlist)
		// System.out.println(mm);
	}

	private static String readUrl() throws Exception {// api로 json받아오기
		BufferedInputStream reader = null;

		// 어제날짜의 String 값
		Calendar calendar1 = new GregorianCalendar();
		calendar1.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yesterday = sdf.format(calendar1.getTime());
		try { // 키1:c1635cef98ea88052ed1414320cbccc4 /
				// 키2:f88c5ef2439ea108f680e0016b2abb37
			URL url = new URL(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" + "searchDailyBoxOfficeList.json"
							+ "?key=c1635cef98ea88052ed1414320cbccc4" + "&targetDt=" + yesterday);
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while ((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private static String readInfoUrl(String mCode) throws Exception {
		BufferedInputStream reader1 = null;
		try {
			URL InfoUrl = new URL(" http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"
					+ "?key=c1635cef98ea88052ed1414320cbccc4" + "&movieCd=" + mCode);
			reader1 = new BufferedInputStream(InfoUrl.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while ((i = reader1.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader1 != null)
				reader1.close();
		}

	}

	public ArrayList<MovieBoxInfo> getMlist() {
		return mlist;
	}

	public void setMlist(ArrayList<MovieBoxInfo> mlist) {
		this.mlist = mlist;
	}
}
