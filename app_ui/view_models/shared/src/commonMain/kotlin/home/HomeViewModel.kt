package home

import view_model.BaseViewModel

class HomeViewModel: BaseViewModel<State, Event, Effect>(initialValue = State.Default)
