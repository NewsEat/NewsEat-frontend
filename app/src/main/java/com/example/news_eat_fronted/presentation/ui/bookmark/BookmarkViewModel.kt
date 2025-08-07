package com.example.news_eat_fronted.presentation.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.news_eat_fronted.presentation.model.BookmarkItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookmarkViewModel  : ViewModel() {
    // 더미 데이터
    private val _bookmarkList = MutableStateFlow(
        listOf(
            BookmarkItem(
                title = "혈당 주범 흰 쌀밥…\"이렇게 먹으면 '슈퍼푸드' 된다\"",
                category = "IT/과학",
                date = "2025.08.06",
                thumbnailUrl = "https://cphoto.asiae.co.kr/listimglink/1/2025051719272061633_1747477641.jpg",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "'데이비슨과 결별' 롯데, 벨라스케즈 영입 공식 발표…33만달러 계약",
                category = "스포츠",
                date = "2025.08.05",
                thumbnailUrl = "https://imgnews.pstatic.net/image/003/2025/08/07/NISI20250807_0001912788_web_20250807103749_20250807104215055.jpg?type=w647",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "정치",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "경제",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "사회",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "생활/문화",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "연예",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            ),
            BookmarkItem(
                title = "뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다. 뉴스 제목 입니다.",
                category = "세계",
                date = "2025.08.05",
                thumbnailUrl = "https://raw.githubusercontent.com/NewsEat/assets/refs/heads/main/news_eat_logo.png",
                isBookmarked = true
            )
        )
    )

    val bookmarkList: StateFlow<List<BookmarkItem>> = _bookmarkList

    fun updateBookmarkStatus(item: BookmarkItem) {
        val updatedList = _bookmarkList.value.map {
            if (it == item) it.copy(isBookmarked = !it.isBookmarked) else it
        }
        _bookmarkList.value = updatedList
    }
}