package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;

public class SampleTest extends TestCase {

	private final static String DATA_DIR = "./src/test/resources/data/";

	public void test1() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IOException {
		FileInputStream fis = new FileInputStream(DATA_DIR + "test1.txt");
		StringWriter sw = new StringWriter();
		int c;
		do {
			c = fis.read();
			if (c != -1) {
				sw.append((char) c);
			}
		} while (c != -1);
		sw
				.append("{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}");
		String json = sw.toString();
		JSONObject jsonObject = JSONObject.fromObject(json);
		DynaBean main = (DynaBean) JSONObject.toBean(jsonObject);
		assertNotNull(main);
		DynaBean website = (DynaBean) main.get("website");
		assertNotNull(website);
		assertEquals("La la la", website.get("title"));
		DynaBean color = (DynaBean) website.get("color");
		assertEquals("aaa", color.get("bg"));
		assertEquals("bbb", color.get("fg"));
		@SuppressWarnings("unchecked")
		ArrayList<DynaBean> menue = (ArrayList<DynaBean>) main.get("menue");
		assertNotNull(menue);
		assertEquals(3, menue.size());
		assertEquals("Main", menue.get(0).get("title"));
		assertEquals("main.html", menue.get(0).get("content"));
		assertEquals("Gallery", menue.get(1).get("title"));
		assertEquals("gallery.html", menue.get(1).get("content"));
		assertEquals("Links", menue.get(2).get("title"));
		assertEquals("links.html", menue.get(2).get("content"));
	}
}
