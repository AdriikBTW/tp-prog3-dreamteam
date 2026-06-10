package prog3.tp.view;

import prog3.tp.model.SolverResult;
import prog3.tp.presenter.Presenter;

public interface View {
    public void setPresenter(Presenter presenter);

    public void update();

    void showSolving();

    void showResult(SolverResult result);
}
