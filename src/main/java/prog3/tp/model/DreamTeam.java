package prog3.tp.model;

import prog3.tp.presenter.Observer;

public class DreamTeam implements Model {
    private Observer _observer;

    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }
}
