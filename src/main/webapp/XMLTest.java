import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTest {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/4d4f4d70577a617a3432717a53574b/xml/TbPublicWifiInfo/1/100/");
			InputStream stream = url.openStream();
			char ch = 0;
			DocumentBuilderFactory dbFactory = 
					DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = 
					dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			System.out.println("Root element:" +
						doc.getDocumentElement().getNodeName());

			NodeList aList = doc.getElementsByTagName("TbPublicWifiInfo"); //총 데이터 건수 출력
			Node aNode = aList.item(0);
			Element aElement = (Element) aNode;
			System.out.println(getTagValue("list_total_count", aElement)); //총 데이터 건수 출력 종료
			
			NodeList nList = doc.getElementsByTagName("row"); // 출력값
			System.out.println("--------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("==========================");

			
					System.out.println("거리 " +
							getTagValue("X_SWIFI_MGR_NO", eElement));
					
					System.out.println("관리번호 " +
							getTagValue("X_SWIFI_MGR_NO", eElement));
					
					System.out.println("자치구 " +
							getTagValue("X_SWIFI_WRDOFC", eElement));
					
					System.out.println("와이파이명 " +
							getTagValue("X_SWIFI_MAIN_NM", eElement));
					
					System.out.println("도로명주소 " +
							getTagValue("X_SWIFI_ADRES1", eElement));
					
					System.out.println("상세주소 " +
							getTagValue("X_SWIFI_ADRES2", eElement));
					
					System.out.println("설치위치 ");
					
					System.out.println("설치유형 " +
							getTagValue("X_SWIFI_INSTL_TY", eElement));
					
					System.out.println("설치기관 " +
							getTagValue("X_SWIFI_INSTL_MBY", eElement));
					
					System.out.println("망종류 " +
							getTagValue("X_SWIFI_CMCWR", eElement));
					
					System.out.println("설치년도 " +
							getTagValue("X_SWIFI_CNSTC_YEAR", eElement));
					
					System.out.println("실내외구분 " +
							getTagValue("X_SWIFI_INOUT_DOOR", eElement));
					
					System.out.println("와이파이 접속 환경 ");
					
					System.out.println("X좌표 " +
							getTagValue("LAT", eElement));
					
					System.out.println("Y좌표 " +
							getTagValue("LNT", eElement));
					
					System.out.println("작업일자 " +
							getTagValue("WORK_DTTM", eElement));
					System.out.println("==========================");
					System.out.println();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		// TODO Auto-generated method stub
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		return nValue.getNodeValue();
	}
}
