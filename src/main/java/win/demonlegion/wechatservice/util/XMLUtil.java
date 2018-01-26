package win.demonlegion.wechatservice.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * XML工具类
 * Created by MK on 2017/6/25.
 */
public class XMLUtil implements Serializable {
    private static final long serialVersionUID = -7187735980069624097L;
    private static final Logger logger = LoggerFactory.getLogger(XMLUtil.class);

    public static String toXML(Object bean) {
        Map<String, Object> params = BeanMapUtil.beanToMapByJSON(bean);

        //root element
        Element xmlElement = new Element("xml");
        Document doc = new Document(xmlElement);

        Set<String> keySet = params.keySet();

        for(String key : keySet) {
            if(params.get(key) != null) {
                Element element = new Element(key);
                element.setText(params.get(key).toString());
                xmlElement.addContent(element);
            }
        }

        XMLOutputter xmlOutput = new XMLOutputter();
        return xmlOutput.outputString(doc);
    }

    public static <T> T parseXML(String xml, Class<T> clazz) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<>();
            SAXBuilder builder = new SAXBuilder();
            StringReader reader = new StringReader(xml);
            InputSource source = new InputSource(reader);
            Document doc = builder.build(source);
            Element xmlElement = doc.getRootElement(); //获取根元素下面的所有子元素之间的全部元素
            List<Element> elements = xmlElement.getChildren();
            for(Element element : elements) {
                map.put(element.getName(), element.getText());
            }
        } catch(JDOMException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return BeanMapUtil.mapToBeanByJSON(map, clazz);
    }
}
