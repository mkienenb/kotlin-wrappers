package example.zegreatrob.jsmints

import kotlinx.coroutines.await
import org.w3c.dom.HTMLElement

interface ScreenByTestId : ByTestId {

    val screen: Screen

    override fun getByTestId(text: String) = screen.getByTestId(text)
    override fun getAllByTestId(text: String) = screen.getAllByTestId(text)
    override fun queryByTestId(text: String) = screen.queryByTestId(text)
    override fun queryAllByTestId(text: String) = screen.queryAllByTestId(text)
    override suspend fun findByTestId(text: String) = screen.findByTestId(text).await()
    override suspend fun findAllByTestId(text: String) = screen.findAllByTestId(text).await()
}

internal interface ScreenTestingLibraryQueries :
    TestingLibraryQueries,
//    ScreenByRole,
//    ScreenByText,
//    ScreenByLabelText,
//    ScreenByAltText,
    ScreenByTestId {
    companion object : ScreenTestingLibraryQueries {
        override val screen = reactTestingLibrary.screen
    }
}

interface TestingLibraryQueries :
//    ByRole,
//    ByText,
//    ByLabelText,
//    ByAltText,
    ByTestId

interface ByTestId {
    fun getByTestId(text: String): HTMLElement
    suspend fun findByTestId(text: String): HTMLElement
    fun queryByTestId(text: String): HTMLElement?
    fun getAllByTestId(text: String): Array<HTMLElement>
    fun queryAllByTestId(text: String): Array<HTMLElement>
    suspend fun findAllByTestId(text: String): Array<HTMLElement>
}
