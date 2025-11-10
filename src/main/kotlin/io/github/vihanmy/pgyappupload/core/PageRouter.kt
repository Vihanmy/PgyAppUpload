package io.github.vihanmy.pgyappupload.core

import androidx.compose.runtime.mutableStateListOf
import io.github.vihanmy.pgyappupload.model.Page

class PageRouter {
    val pageStack = mutableStateListOf<Page>()

    init {
        pageStack.add(Page.Home)
    }

    fun pop() {
        if (pageStack.size == 1) return
        pageStack.removeLastOrNull()
    }

    fun push(page: Page) {
        pageStack.add(page)
    }
}