package wuye.manager.utils;

public class StringUtil {

	public final static String getImg(String db) {
		if(db!=null && db.endsWith(".jpg")) {
			return new StringBuilder("http://123.59.100.61:8082/api/upload/download?name=").append(db).toString();
		}
		return db;
	}
}
