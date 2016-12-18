package server.api;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import vos.MovieSearchInfo;

public class SearchBy { // 제목으로 검색(1) 또는 감독(2)으로 검색

	private String thumbnail;
	private String story;
	private String title;
	private String genre;
	private String showTm;
	private String actor;
	private String director;
	private ArrayList<String> codeList;
	private ArrayList<String> storyList;
	private ArrayList<String> titleList;
	private ArrayList<String> genreList;
	private ArrayList<String> thumbnailList;
	private ArrayList<String> showTmList;
	private ArrayList<String> actorList;
	private ArrayList<MovieSearchInfo> searchList;
	private ArrayList<String> directorList;

	public SearchBy() {

		// try {
		// Search(2, "크리스토퍼 놀란");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public ArrayList<MovieSearchInfo> getSearchList() {
		return searchList;
	}

	public void setSearchList(ArrayList<MovieSearchInfo> searchList) {
		this.searchList = searchList;
	}

	public void Search(int type, String name) throws Exception {

		codeList = new ArrayList<String>();
		storyList = new ArrayList<String>();
		titleList = new ArrayList<String>();
		genreList = new ArrayList<String>();
		thumbnailList = new ArrayList<String>();
		showTmList = new ArrayList<String>();
		actorList = new ArrayList<String>();
		directorList = new ArrayList<String>(); // 이거

		if (type == 2) {
			codeList = jsonParsingforKodis("directorNm", name); // 감독으로 코드 받아오기
		} else if (type == 1) {
			codeList = jsonParsingforKodis("movieNm", name); // 제목으로 코드 받아오기
		}

		for (String code : codeList) { // 영화제목 검색
			title = jsonParsingforKodis2("movieNm", code);
			titleList.add(title);
		}

		for (String code : codeList) { // 장르 검색
			genre = jsonParsingforKodis2("genres", code);
			genreList.add(genre);
		}

		for (String code : codeList) { // 상영시간 검색
			showTm = jsonParsingforKodis2("showTm", code);
			showTmList.add(showTm);
		}

		for (String code : codeList) { // 배우 검색
			actor = jsonParsingforKodis2("actors", code);
			actorList.add(actor);
		}

		for (String title : titleList) { // 스토리 검색
			story = jsonParsingforDaum("story", title);
			storyList.add(story);
		}

		for (String title : titleList) { // 섬네일 검색
			thumbnail = jsonParsingforDaum("thumbnail", title);
			thumbnailList.add(thumbnail);
		}

		for (String code : codeList) { // 감독검색
			director = jsonParsingforKodis2("directors", code);
			directorList.add(director);
		}

		searchList = new ArrayList();

		for (int i = 0; i < codeList.size(); i++) {
			searchList.add(new MovieSearchInfo(codeList.get(i), titleList.get(i), genreList.get(i), directorList.get(i),
					actorList.get(i), thumbnailList.get(i), storyList.get(i), showTmList.get(i)));
		}

	}

	public String jsonParsingforDaum(String pra, String title) throws Exception { // 다음에서
																					// 섬네일과
																					// 줄거리
		String result = "";

		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobject = (JSONObject) jsonparser.parse(readDaumUrl(title));
		JSONObject json = (JSONObject) jsonobject.get("channel");
		JSONArray array = (JSONArray) json.get("item");
		for (int i = 0; i < array.size(); i++) {
			JSONObject entity = (JSONObject) array.get(i);
			JSONArray storyArray = (JSONArray) entity.get(pra);// story 또는
																// thumbnail
			for (int j = 0; j < storyArray.size(); j++) {
				JSONObject info = (JSONObject) storyArray.get(j);
				result = (String) info.get("content");
				return result;
			}
		}
		return result;
	}

	public ArrayList<String> jsonParsingforKodis(String url, String name) throws Exception { // 영진회에서
																								// 감독으로
																								// 코드
		String result = "";

		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobject = (JSONObject) jsonparser.parse(readKobisUrl(url, name));
		JSONObject json = (JSONObject) jsonobject.get("movieListResult");
		JSONArray array = (JSONArray) json.get("movieList");
		for (int i = 0; i < array.size(); i++) {
			JSONObject entity = (JSONObject) array.get(i);
			result = (String) entity.get("movieCd");
			codeList.add(result);
		}
		return codeList;
	}

	public String jsonParsingforKodis2(String pra, String code) throws Exception { // 영진회에서
																					// 코드로
																					// 정보들
		String result = "";
		JSONParser jsonparser2 = new JSONParser();
		JSONObject jsonobject2 = (JSONObject) jsonparser2.parse(readInfoUrl(code));
		JSONObject json1 = (JSONObject) jsonobject2.get("movieInfoResult");
		JSONObject json2 = (JSONObject) json1.get("movieInfo");
		if (pra.equals("genres")) {
			JSONArray array = (JSONArray) json2.get("genres");
			for (int i = 0; i < array.size(); i++) {
				JSONObject entity = (JSONObject) array.get(i);
				result = (String) entity.get("genreNm");
				return result;
			}
		} else if (pra.equals("actors") || pra.equals("directors")) {// 이거
			JSONArray array = (JSONArray) json2.get(pra);
			for (int i = 0; i < array.size(); i++) {
				JSONObject entity = (JSONObject) array.get(i);
				result = (String) entity.get("peopleNm");
				return result;
			}
		} else {
			result = (String) json2.get(pra);
			return result;
		}
		return result;
	}

	private static String readKobisUrl(String addUrl, String name) throws Exception { // 영진회에서
																						// 감독
																						// 또는
																						// 제목
																						// 으로
																						// 코드
		BufferedInputStream reader1 = null;

		name = URLEncoder.encode(name, "UTF-8");

		try { // 키1:c1635cef98ea88052ed1414320cbccc4 /
				// 키2:f88c5ef2439ea108f680e0016b2abb37
			URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"
					+ "?key=c1635cef98ea88052ed1414320cbccc4" + "&" + addUrl + "=" + name + "&itemPerPage=" + 30);
			reader1 = new BufferedInputStream(url.openStream());
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

	private static String readDaumUrl(String q) throws Exception { // 다음에서 제목으로
		BufferedInputStream reader = null;

		q = URLEncoder.encode(q, "UTF-8");

		String daumUrl = "http://apis.daum.net/contents/movie";
		daumUrl += "?apikey=" + "a188d2c93898808992de298cdd35a91a"; // 발급된 키
		daumUrl += "&q=" + q; // 검색어
		daumUrl += "&result=" + "1"; // 출력될 결과수
		daumUrl += "&pageno=" + "1"; // 페이지 번호
		daumUrl += "&output=" + "json";

		try {
			URL url = new URL(daumUrl);
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

	private static String readInfoUrl(String code) throws Exception { // 영진회에서
																		// 코드로
																		// 검색
		BufferedInputStream reader1 = null;
		try {
			URL url = new URL(" http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"
					+ "?key=c1635cef98ea88052ed1414320cbccc4" + "&movieCd=" + code);
			reader1 = new BufferedInputStream(url.openStream());
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

	//////////////////////////// list들 getter///////////////필요한가..
	public ArrayList<String> getCodeList() {
		return codeList;
	}

	public ArrayList<String> getStoryList() {
		return storyList;
	}

	public ArrayList<String> getTitleList() {
		return titleList;
	}

	public ArrayList<String> getGenreList() {
		return genreList;
	}

	public ArrayList<String> getThumbnailList() {
		return thumbnailList;
	}

	public ArrayList<String> getShowTmList() {
		return showTmList;
	}

	public ArrayList<String> getActorList() {
		return actorList;
	}

}
