import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.netology.basket.Basket;
import ru.netology.basket.ClientLog;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket = new Basket(new String[]{"Молоко", "Хлеб", "Яблоко", "Гречневая крупа", "Сок"},
                new int[]{64, 50, 100, 40, 150});
        ClientLog clientLog = new ClientLog();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("shop.xml");

            XPath xPath = XPathFactory.newInstance().newXPath();

            boolean doLoad = Boolean.parseBoolean(xPath
                    .compile("/config/load/enabled")
                    .evaluate(doc));
            String loadFileName = xPath
                    .compile("/config/load/fileName")
                    .evaluate(doc);

            String loadFormat = xPath
                    .compile("/config/load/format")
                    .evaluate(doc);
            File loadFile = new File(loadFileName);

            if (doLoad) {
                switch (loadFormat) {
                    case "json": basket = Basket.loadFromJson(loadFile);
                        break;
                }
            } else {
                basket.groceryBasket();
            }


            boolean doSave = Boolean.parseBoolean(xPath
                    .compile("/config/save/enabled")
                    .evaluate(doc));
            String saveFileName = xPath
                    .compile("/config/save/fileName")
                    .evaluate(doc);

            String saveFormat = xPath
                    .compile("/config/save/format")
                    .evaluate(doc);
            File saveFile = new File(saveFileName);
            if (doSave) {
                switch (saveFormat) {
                    case "json": basket.saveJson(saveFile);;
                        break;
                }}


                boolean doLog = Boolean.parseBoolean(xPath
                        .compile("/config/log/enabled")
                        .evaluate(doc));
                String logFileName = xPath
                        .compile("/config/log/fileName")
                        .evaluate(doc);

                File logFile = new File(logFileName);
//
                if (doLog) {
                        clientLog.exportAsCSV(logFile);
                }

} catch (XPathExpressionException e) {
        throw new RuntimeException(e);
    } catch (ParserConfigurationException e) {
        throw new RuntimeException(e);
    } catch (SAXException e) {
        throw new RuntimeException(e);
    }
}}
