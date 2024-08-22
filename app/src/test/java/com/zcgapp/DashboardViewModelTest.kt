import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zcgapp.model.DashboardItem
import com.zcgapp.model.Item
import com.zcgapp.ui.repository.DashboardRepository
import com.zcgapp.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

import java.net.UnknownHostException

class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository: DashboardRepository = mock(DashboardRepository::class.java)
    private val viewModel = DashboardViewModel(repository)

    @Test
    fun `loadDashboardItems successfully fetches data`() = runTest(testDispatcher) {
        // Given
        val mockData = listOf(
            DashboardItem(
                sectionType = "horizontalFreeScroll",
                items = listOf(
                    Item(
                        image = "https://example.com/image1.jpg",
                        title = "Item 1"
                    ),
                    Item(
                        image = "https://example.com/image2.jpg",
                        title = "Item 2"
                    )
                )
            ),
            DashboardItem(
                sectionType = "splitBanner",
                items = listOf(
                    Item(
                        image = "https://example.com/banner1.jpg",
                        title = "Banner 1"
                    ),
                    Item(
                        image = "https://example.com/banner2.jpg",
                        title = "Banner 2"
                    )
                )
            ),
            DashboardItem(

                sectionType = "banner",
                items = listOf(
                    Item(
                        image = "https://example.com/banner3.jpg",
                        title = "Banner 3"
                    )
                )
            )
        )
        `when`(repository.fetchDashboardData()).thenReturn(mockData)

        // When
        viewModel.loadDashboardItems()

        // Then
        assertEquals(mockData, viewModel.dashboardItems.value)
        assertNull(viewModel.errorMessage.value)
        assertEquals(false, viewModel.loading.value)
    }

    @Test
    fun `loadDashboardItems handles no internet connection`() = runTest(testDispatcher) {
        // Given
        `when`(repository.fetchDashboardData()).thenThrow(UnknownHostException())

        // When
        viewModel.loadDashboardItems()

        // Then
        assertEquals("No internet connection. Please check your connection and try again.", viewModel.errorMessage.value)
        assertEquals(false, viewModel.loading.value)
    }

    @Test
    fun `loadDashboardItems handles general exception`() = runTest(testDispatcher) {
        // Given
        `when`(repository.fetchDashboardData()).thenThrow(Exception("Some error"))

        // When
        viewModel.loadDashboardItems()

        // Then
        assertEquals("An error occurred: Some error", viewModel.errorMessage.value)
        assertEquals(false, viewModel.loading.value)
    }
}