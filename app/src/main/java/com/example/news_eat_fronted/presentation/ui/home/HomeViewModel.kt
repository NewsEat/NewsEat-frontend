package com.example.news_eat_fronted.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.presentation.model.CategoryItem
import com.example.news_eat_fronted.presentation.model.HomeNewsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _nickname = MutableStateFlow("쫀득물만두")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(listOf("경제", "연예"))
    val interests: StateFlow<List<String>> = _interests

    val categoryListMap = mapOf(
        "정치" to CategoryItem(R.drawable.category_politics_unselected, R.string.category_politics),
        "경제" to CategoryItem(R.drawable.category_economy_unselected, R.string.category_economy),
        "사회" to CategoryItem(R.drawable.category_social_unselected, R.string.category_social),
        "생활/문화" to CategoryItem(R.drawable.category_culture_unselected, R.string.category_culture),
        "IT/과학" to CategoryItem(R.drawable.category_science_unselected, R.string.category_science),
        "연예" to CategoryItem(R.drawable.category_entertainment_unselected, R.string.category_entertainment),
        "스포츠" to CategoryItem(R.drawable.category_sports_unselected, R.string.category_sports),
        "세계" to CategoryItem(R.drawable.category_world_unselected, R.string.category_world),
    )

    private val _recommendList = MutableStateFlow<List<HomeNewsItem>>(emptyList())
    val recommendList: StateFlow<List<HomeNewsItem>> = _recommendList

    private val _category1List = MutableStateFlow<List<HomeNewsItem>>(emptyList())
    val category1List: StateFlow<List<HomeNewsItem>> = _category1List

    private val _category2List = MutableStateFlow<List<HomeNewsItem>>(emptyList())
    val category2List: StateFlow<List<HomeNewsItem>> = _category2List

    private val _category3List = MutableStateFlow<List<HomeNewsItem>>(emptyList())
    val category3List: StateFlow<List<HomeNewsItem>> = _category3List

    private val _positiveList = MutableStateFlow<List<HomeNewsItem>>(emptyList())
    val positiveList: StateFlow<List<HomeNewsItem>> = _positiveList

    init {
        // 더미데이터 초기화
        _recommendList.value = listOf(
            HomeNewsItem(id = 1, "https://cphoto.asiae.co.kr/listimglink/1/2025051719272061633_1747477641.jpg", "‘던파’ 흥행에 넥슨 네오플 평균 연봉 2.2억원…업계 1위-뉴스제목입니다 최대 두줄노출"),
            HomeNewsItem(id = 2, "https://imgnews.pstatic.net/image/003/2025/08/07/NISI20250807_0001912788_web_20250807103749_20250807104215055.jpg?type=w647", "실리콘밸리에 VC 만드는 네이버…\"AI 총력전\""),
            HomeNewsItem(id = 3, "https://file2.nocutnews.co.kr/newsroom/image/2024/04/01/202404011752096007_0.jpg", "이제 겨우 스물네살인데, 영구결번 전설적 투수를 넘어섰다…역사를 쓰는 정해영"),
            HomeNewsItem(id = 4, "https://file2.nocutnews.co.kr/newsroom/image/2025/05/17/202505171702116972_0.jpg", "\"김혜성, 마법 지팡이라도 들고 있는 것 같아\" ML 명장도 인정한 존재감, 빅리그 잔류 가능성 높아지나"),
            HomeNewsItem(id = 5, "https://img.khan.co.kr/news/2022/08/01/l_2022080201000076500004271.webp", "'불닭' 파워에 주가 폭등한 삼양식품, 사옥도 '이곳'으로 옮긴다")
        )

        _category1List.value = listOf(
            HomeNewsItem(id = 1, "https://cphoto.asiae.co.kr/listimglink/1/2025051719272061633_1747477641.jpg", "111‘던파’ 흥행에 넥슨 네오플 평균 연봉 2.2억원…업계 1위-뉴스제목입니다 최대 두줄노출"),
            HomeNewsItem(id = 2, "https://imgnews.pstatic.net/image/003/2025/08/07/NISI20250807_0001912788_web_20250807103749_20250807104215055.jpg?type=w647", "222실리콘밸리에 VC 만드는 네이버…\"AI 총력전\""),
            HomeNewsItem(id = 3, "https://file2.nocutnews.co.kr/newsroom/image/2024/04/01/202404011752096007_0.jpg", "333이제 겨우 스물네살인데, 영구결번 전설적 투수를 넘어섰다…역사를 쓰는 정해영"),
            HomeNewsItem(id = 4, "https://file2.nocutnews.co.kr/newsroom/image/2025/05/17/202505171702116972_0.jpg", "444김혜성, 마법 지팡이라도 들고 있는 것 같아 ML 명장도 인정한 존재감, 빅리그 잔류 가능성 높아지나"),
            HomeNewsItem(id = 5, "https://img.khan.co.kr/news/2022/08/01/l_2022080201000076500004271.webp", "'555불닭' 파워에 주가 폭등한 삼양식품, 사옥도 '이곳'으로 옮긴다")
        )

        _category2List.value = listOf(
            HomeNewsItem(id = 1, "https://cdn.dailycnc.com/news/photo/202303/217256_225001_441.png", "[겜쇼분석] 던파모바일과 함께한 1주년! 모바일 액션 RPG의 ‘새로운 도약’은?"),
            HomeNewsItem(id = 2, "https://img.khan.co.kr/news/r/700xX/2025/08/08/news-p.v1.20250808.c6b77c2b896f4e48b7feffba32eed5ee_P1.webp", "김문수 “윤석열 인권 짓밟더니 범죄자 조국에겐 꽃길 깔아줘” 사면 논의 비판"),
            HomeNewsItem(id = 3, "https://img.khan.co.kr/news/r/700xX/2025/08/08/news-p.v1.20250808.6d3e2885b78645dd83e1605368982f08_P1.webp", "충주서 대몽항쟁 역사 담긴 고려시대 토성 출토…충주시, 학술조사 실시"),
            HomeNewsItem(id = 4, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250808.c486d95163984355b0974fc4a20a46b5_P1.webp", "열대야 잊게 할 우주쇼…증평 좌구산 천문대 ‘페르세우스 유성우 관측 행사’"),
            HomeNewsItem(id = 5, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250808.0ce60b31320a4d7995fef0037e8f3c92_P1.webp", "'찌는 여름···베스트셀러 순위에서 만화책 약진")
        )
//
//        _category3List.value = listOf(
//            HomeNewsItem(id = 1, "https://cdn.dailycnc.com/news/photo/202303/217256_225001_441.png", "111[겜쇼분석] 던파모바일과 함께한 1주년! 모바일 액션 RPG의 ‘새로운 도약’은?"),
//            HomeNewsItem(id = 2, "https://img.khan.co.kr/news/r/700xX/2025/08/08/news-p.v1.20250808.c6b77c2b896f4e48b7feffba32eed5ee_P1.webp", "222김문수 “윤석열 인권 짓밟더니 범죄자 조국에겐 꽃길 깔아줘” 사면 논의 비판"),
//            HomeNewsItem(id = 3, "https://img.khan.co.kr/news/r/700xX/2025/08/08/news-p.v1.20250808.6d3e2885b78645dd83e1605368982f08_P1.webp", "333충주서 대몽항쟁 역사 담긴 고려시대 토성 출토…충주시, 학술조사 실시"),
//            HomeNewsItem(id = 4, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250808.c486d95163984355b0974fc4a20a46b5_P1.webp", "444열대야 잊게 할 우주쇼…증평 좌구산 천문대 ‘페르세우스 유성우 관측 행사’"),
//            HomeNewsItem(id = 5, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250808.0ce60b31320a4d7995fef0037e8f3c92_P1.webp", "555'찌는 여름···베스트셀러 순위에서 만화책 약진")
//        )

        _positiveList.value = listOf(
            HomeNewsItem(id = 1, "https://img.khan.co.kr/news/2025/08/07/l_2025080801000200500019641.webp", "화장실 왜 또 가냐고? 여성의 방광이 답한다"),
            HomeNewsItem(id = 2, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250807.dc17e1d9b1de4f2885fbc7b17fa0eebf_P1.webp", "강릉 의원서 허리 시술 뒤 ‘집단 이상 증상’…22명으로 늘어"),
            HomeNewsItem(id = 3, "https://img.khan.co.kr/news/r/700xX/2025/08/06/news-p.v1.20250806.eff8a77b17c84238bb236974f9c8708d_P1.webp", "에어부산, 7~13일 일본 항공권 최저 3만원대 특가"),
            HomeNewsItem(id = 4, "https://img.khan.co.kr/news/2025/08/08/news-p.v1.20250808.e9de78d092df46ceaf27f8600d5a2134_P1.webp", "은마 맞은편 ‘대치쌍용1차’ 최고 49층 999가구 단지로 탈바꿈"),
            HomeNewsItem(id = 5, "https://img.khan.co.kr/news/r/700xX/2025/08/08/news-p.v1.20250808.3b2d97dd2fe54a538d5895532868a09c_P1.webp", "“박사급 전문가 수준”···오픈 AI, 최신 AI 모델 ‘GPT-5’ 공개")
        )
    }
}