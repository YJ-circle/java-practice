package com.thelightway.tmdb.naver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Web {
	public static void main(String[] args) {
		
		try {
			String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EC%9B%94%EC%9A%94%EB%93%9C%EB%9D%BC%EB%A7%88";
			
			Document doc = Jsoup.connect(url).get();
			
			
			Elements elements = doc.select(".cs_common_module .list_type ~ .cm_content_wrap .box_card_image_list .list_info .title._ellipsis");
			String text;
			for (int i = 0; i < elements.size(); i++) {
				text = elements.get(i).text();
				System.out.println("제목: " + text);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
