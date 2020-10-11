package maskbook.maskshop.service;

import maskbook.maskshop.domain.CoronaBoard;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaBoardService {

    private static String coronaDataUrl = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";

    public List<CoronaBoard> getCoronaData() throws IOException {

        List<CoronaBoard> coronaBoardList = new ArrayList<>();

        Document doc = Jsoup.connect(coronaDataUrl).get();
        Elements contents = doc.select("table tbody tr");

        for(Element content : contents){
            Elements tdData = content.select("td");

            if(content.select("th").text().equals("검역")){
                continue;
            }

            CoronaBoard coronaBoard = CoronaBoard.builder()
                    .cityName(content.select("th").text())
                    .yesterdayTodayDiff(Integer.parseInt(tdData.get(0).text()))
                    .confirmedPersion(Integer.parseInt(tdData.get(3).text().replaceAll(",","")))
                    .underTreatment(Integer.parseInt(tdData.get(4).text().replaceAll(",","")))
                    .treatementRelease(Integer.parseInt(tdData.get(5).text().replaceAll(",","")))
                    .diedPersion(Integer.parseInt(tdData.get(6).text()))
                    .coronaRatio(Float.parseFloat(tdData.get(7).text()))
                    .build();

            coronaBoardList.add(coronaBoard);
        }
        return coronaBoardList;
    }
}
