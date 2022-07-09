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
			URL url = new URL("http://openapi.seoul.go.kr:8088/4d4f4d70577a617a3432717a53574b/xml/TbPublicWifiInfo/1/20/");
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
			NodeList nList = doc.getElementsByTagName("row");
			System.out.println("--------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					System.out.println("관리번호" +
							getTagValue("X_SWIFI_MGR_NO", eElement));
					
					System.out.println("자치구" +
							getTagValue("X_SWIFI_WRDOFC", eElement));
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
