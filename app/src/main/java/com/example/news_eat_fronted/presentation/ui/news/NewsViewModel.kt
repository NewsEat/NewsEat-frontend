package com.example.news_eat_fronted.presentation.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_eat_fronted.presentation.model.NewsDetailItem

class NewsViewModel : ViewModel() {

    private val _selectedNewsTitle = MutableLiveData<String>()
    val selectedNewsTitle: LiveData<String> = _selectedNewsTitle

    private val _selectedNewsImgResId = MutableLiveData<String>()
    val selectedNewsImgResId: LiveData<String> = _selectedNewsImgResId

    private val _selectedNewsPublisher = MutableLiveData<String>()
    val selectedNewsPublisher: LiveData<String> = _selectedNewsPublisher

    private val _selectedNewsDate = MutableLiveData<String>()
    val selectedNewsDate: LiveData<String> = _selectedNewsDate

    private val _selectedNewsCategory = MutableLiveData<String>()
    val selectedNewsCategory: LiveData<String> = _selectedNewsCategory

    private val _selectedNewsSentiment = MutableLiveData<String>()
    val selectedNewsSentiment: LiveData<String> = _selectedNewsSentiment

    private val _selectedNewsContent = MutableLiveData<String>()
    val selectedNewsContent: LiveData<String> = _selectedNewsContent

    private val _recommendNewsList = MutableLiveData<List<NewsDetailItem>>()
    val recommendNewsList: LiveData<List<NewsDetailItem>> = _recommendNewsList

    init {
        loadDummyNews()
    }

    private fun loadDummyNews() {
        _selectedNewsTitle.value = "“책임과 업무만 늘어나”…리더 꺼리는 2030 직장인"
        _selectedNewsImgResId.value = "https://cphoto.asiae.co.kr/listimglink/1/2025051719272061633_1747477641.jpg"
        _selectedNewsPublisher.value = "문화일보"
        _selectedNewsDate.value = "2025.05.25"
        _selectedNewsCategory.value = "세계"
        _selectedNewsSentiment.value = "긍정"
        _selectedNewsContent.value = "성공의 척도로 여겨지던 ‘초고속 승진’도 이제 옛말이 되는 것일까. 20~30세대 젊은 직장인 사이에서는 ‘리더를 하지 않겠다’는 분위기가 확산하고 있다. 이른바 ‘리더 포비아’ 혹은 ‘언보싱’이라고 불리는 이 현상은 성과 압박과 업무량 부담을 피하고 싶은 마음에서 비롯된 것으로 분석된다. \u2028\u2028대학내일20대연구소가 발표한 ‘20·30 직장인의 리더 인식 기획조사 2025’에 따르면 공기업과 사기업에 재직 중인 19~36세 850명을 대상으로 조사한 결과, 47.3%는 “향후 리더 역할을 맡지 않아도 불안하지 않다”고 대답했다. 반면 “불안하다”고 답한 비율은 22.1%에 불과했다. \u2028\u2028‘중간관리직을 맡을 의향’에 대해서는 “있다”는 사람과 “없다” 사람이 각각 36.7%와 32.5%로 비슷했다. \u2028\u2028리더 포비아는 리더(Leader)와 공포증을 뜻하는 포비아(Phobia)의 합성어다. 단순히 리더가 되기 싫은 것을 넘어 리더 역할에 대한 심리적인 거부감과 두려움이 높은 상태를 의미한다. 언보싱(Unbossing)은 리더의 역할이나 관리직을 의도적으로 회피하고 개인의 성장과 삶의 균형을 중시하는 경향을 뜻한다. \u2028\u2028그렇다면 리더 직을 기피하는 이유(중복 응답)는 무엇일까. \u2028\u2028참여자들은 성과에 대한 부담(42.8%)을 가장 많이 꼽았다. 리더로 승진 시 늘어나는 업무량(41.6%)도 비슷한 비중을 차지했다. 단순하게 ‘관리 직무가 개인 성향에 맞지 않는다’는 응답도 33.7%를 차지했다. \u2028\u2028다만 기업 형태에 따라 기피 이유는 조금씩 달랐다. 대기업 재직자는 업무량 증가(47.1%)를 가장 많이 꼽았지만, 중소기업 재직자는 ‘팀 성과 책임(42.8%)’을 1위로 들었다. 공기업 재직자는 팀원 성장 책임(48.6%)을 리더 직을 꺼리는 가장 큰 이유로 꼽았다. \u2028\u2028리더 직을 맡겠다는 응답자들은 그 이유로 ▲급여·복지 혜택(41.4%) ▲조직 내 인정(33.3%) ▲경력 개발 기회 30.8% 등을 선택했다. \u2028\u2028리더 역할에 대한 인식은 기업마다 달랐다. 대기업은 목표 설정(36.3%)과 내·외부 조율(34.1%)을, 공기업은 조직문화 조성(40.4%)과 성과 관리(25.8%)를 주요 업무로 봤다. 모든 기업에서 공통으로 꼽은 리더의 역할은 소통과 팀워크 강화였다. 업무 조정과 분배도 중요한 리더 업무로 인식됐다. \u2028\u2028대학내일20대연구소 관계자는 “20~30세대 직장인들이 리더 역할의 필요성을 적게 느끼고 있다”며 “젊은 세대가 승진보다 개인의 삶과 균형을 중시하는 가치관 변화를 반영한 결과로 풀이된다”고 말했다."

        _recommendNewsList.value = listOf(
            NewsDetailItem(id = 1, "https://cphoto.asiae.co.kr/listimglink/1/2025051719272061633_1747477641.jpg", "‘던파’ 흥행에 넥슨 네오플 평균 연봉 2.2억원…업계 1위-뉴스제목입니다 최대 두줄노출", "아시아경제", "2025.05.20", "경제", "긍정", "내용입니다."),
            NewsDetailItem(id = 2, "https://imgnews.pstatic.net/image/003/2025/08/07/NISI20250807_0001912788_web_20250807103749_20250807104215055.jpg?type=w647", "실리콘밸리에 VC 만드는 네이버…\"AI 총력전\"", "아시아경제", "2025.05.20", "경제", "긍정", "내용입니다."),
            NewsDetailItem(id = 3, "https://file2.nocutnews.co.kr/newsroom/image/2024/04/01/202404011752096007_0.jpg", "이제 겨우 스물네살인데, 영구결번 전설적 투수를 넘어섰다…역사를 쓰는 정해영", "아시아경제", "2025.05.20", "경제", "긍정", "내용입니다."),
            NewsDetailItem(id = 4, "https://imgnews.pstatic.net/image/003/2025/08/07/NISI20250807_0001912788_web_20250807103749_20250807104215055.jpg?type=w647", "실리콘밸리에 VC 만드는 네이버…\"AI 총력전\"", "아시아경제", "2025.05.20", "경제", "긍정", "내용입니다."),
            NewsDetailItem(id = 5, "https://file2.nocutnews.co.kr/newsroom/image/2024/04/01/202404011752096007_0.jpg", "이제 겨우 스물네살인데, 영구결번 전설적 투수를 넘어섰다…역사를 쓰는 정해영", "아시아경제", "2025.05.20", "경제", "긍정", "내용입니다."),
        )
    }

    fun selectNews(item: NewsDetailItem) {
        _selectedNewsTitle.value = item.title
        _selectedNewsImgResId.value = item.imgResId
        _selectedNewsPublisher.value = item.publisher
        _selectedNewsDate.value = item.date
        _selectedNewsCategory.value = item.category
        _selectedNewsSentiment.value = item.sentiment
        _selectedNewsContent.value = item.content
    }
}
