import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

/**
 * @author 赵士杰
 * 
 * RestTemplate提供了一系列调用spring mvc rest（或者说 spring rest webservice）接口
 * 包括 get/post/delete/put/
 *
 */
public class Resttemplate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();   
		
//get方式***********************************************************************************************************

//		//参数直接放在URL中
		String message = restTemplate.getForObject("http://localhost:8080/iems/api/restful/jsonfeed", String.class );
		System.out.println(message);
//		
//		
//		//参数使用MAP传递
//		Map<String ,Object> urlVariables = new HashMap<String ,Object>();
//		urlVariables.put("name", "zhaoshijie");
//		urlVariables.put("id", 80);
//		String message2 = restTemplate.getForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate", String.class, urlVariables);
		
		
		
//delete方式***********************************************************************************************************		
		
		//delete方法（注意：delete方法没有返回值，说明，id=0这个参数在服务器端可以不定义该参数，直接使用request获取）
//		restTemplate.delete("http://localhost:8080/yongbarservice/appstore/appgoods/deleteranking?id=0");
		
		
		
		
//post方式***********************************************************************************************************				
		//使用MAP传递参数
//		Map<String ,Object> urlVariables = new HashMap<String ,Object>();
//		urlVariables.put("name", "zhaoshijie");
//		urlVariables.put("id", 80);
//		String message3 = restTemplate.postForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate",null, String.class, urlVariables);
		
		//直接使用URL传递参数
//		String message = restTemplate.postForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate?name=zhaoshijie&id=80",null, String.class );
		
		

//put方式***********************************************************************************************************
		//注意：delete方法没有返回值，说明，id=0这个参数在服务器端可以不定义该参数，直接使用request获取
//		restTemplate.put("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate?name=zhaoshijie&id=80" ,null);
		
		
		
//		System.out.println(message);
//		System.out.println(message2);
//		System.out.println(message3);

	}

}
