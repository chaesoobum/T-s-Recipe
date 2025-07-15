// 뷰모델 클래스
class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state.asStateFlow()

    fun send(intent: MainViewIntent) {
        val currentState = _state.value
        val newState = when (intent) {
            is MainViewIntent.IncrementCountIntent
                -> currentState.copy(count = currentState.count + 1)

            is MainViewIntent.DecrementCountIntent
                -> currentState.copy(count = currentState.count - 1)
        }
        _state.value = newState
    }
}