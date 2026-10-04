package com.example.newsapp.core.domain.pagination

class PaginationHelper<T> {

    fun reset(
        paginationState: PaginationState<T>,
    ): PaginationState<T> {
        return paginationState.copy(
            items = emptyList(),
            pageIndex = 1,
        )
    }

    fun addItems(
        paginationState: PaginationState<T>,
        newItems: List<T>,
        hasNextPage: Boolean? = null,
    ): PaginationState<T> {
        val newPaginationItems = newItems.map { item -> PaginationItem.Item(item) }
        val paginationItems = paginationState.items.toMutableList()

        if (paginationState.pageIndex == 1) {
            paginationItems.clear()
        }
        paginationItems.removePlaceholder()
        paginationItems += newPaginationItems

        return paginationState.copy(
            pageIndex = paginationState.pageIndex.inc(),
            items = paginationItems,
            hasNextPage = hasNextPage ?: paginationState.hasNextPage,
        ).toFirstPageLoaded()
    }

    fun addError(
        paginationState: PaginationState<T>,
    ): PaginationState<T> {
        return if (paginationState.pageIndex > 1) {
            val paginationItems = paginationState.items.toMutableList().apply {
                removePlaceholder()
                add(PaginationItem.Error)
            }

            paginationState.copy(items = paginationItems).toFirstPageLoaded()
        } else {
            paginationState.toFirstPageLoaded()
        }
    }

    fun addLoading(paginationState: PaginationState<T>): PaginationState<T> {
        return if (paginationState.pageIndex > 1 || paginationState.items.lastOrNull() !is PaginationItem.Loading) {
            val paginationItems = paginationState.items.toMutableList()

            paginationItems.removePlaceholder()
            paginationItems.add(PaginationItem.Loading)

            paginationState.copy(items = paginationItems)
        } else {
            paginationState
        }
    }

    fun shouldLoadNewPage(
        paginationState: PaginationState<T>,
        currentItemIndex: Int,
    ): Boolean {
        val isThresholdReached =
            currentItemIndex + PAGINATION_THRESHOLD >= paginationState.items.lastIndex
        return !hasFooter(paginationState) &&
                paginationState.hasNextPage &&
                isThresholdReached
    }

    fun getItemsCount(paginationState: PaginationState<T>): Int {
        return if (hasFooter(paginationState)) {
            paginationState.items.size - 1
        } else {
            paginationState.items.size
        }
    }

    private fun hasFooter(paginationState: PaginationState<T>): Boolean {
        val lastItem = paginationState.items.lastOrNull() ?: return false
        return lastItem !is PaginationItem.Item<T>
    }

    private fun PaginationState<T>.toFirstPageLoaded(): PaginationState<T> {
        return if (isFirstPageLoaded) this else copy(isFirstPageLoaded = true)
    }

    private fun MutableList<PaginationItem<T>>.removePlaceholder() {
        if (this.isNotEmpty() && this.last() !is PaginationItem.Item<T>) this.removeAt(lastIndex)
    }

    private companion object {
        const val PAGINATION_THRESHOLD = 15
    }
}