package prog3.tp.view;

import java.util.List;
import prog3.tp.presenter.EmployeeViewData;
import prog3.tp.presenter.Presenter;

public interface View {
    public void setPresenter(Presenter presenter);

    public void update();

    void showSolving();

    void showResult(List<EmployeeViewData> result);
}
