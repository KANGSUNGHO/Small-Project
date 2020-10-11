package maskbook.maskshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class CoronaBoard {

    private String cityName;  // 시도명

    private int yesterdayTodayDiff; // 전일대비확진자증감
    private int confirmedPersion; // 확진자
    private int underTreatment; // 격리중
    private int treatementRelease; // 격리해제
    private int diedPersion; // 사망자
    private float coronaRatio; // 발생률



}
