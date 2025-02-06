package ru.sicampus.bootcamp2025.ui.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sicampus.bootcamp2025.domain.list.ListEntity

class ListPagingSource(
    private val request: suspend (pageNum: Int, pageSize: Int) -> Result<List<ListEntity>>
) : PagingSource<Int, ListEntity>()  {
    override fun getRefreshKey(state: PagingState<Int, ListEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListEntity> {
        val pageNum = params.key ?: 0
        return request.invoke(
            pageNum,
            params.loadSize
        ).fold(
            onSuccess = { value ->
                LoadResult.Page(
                    data = value,
                    prevKey = (pageNum - 1).takeIf { it >= 0 },
                    nextKey = (pageNum + 1).takeIf { value.size == params.loadSize }
                )
            },
            onFailure = { error ->
                LoadResult.Error(error)
            }
        )
    }
}
