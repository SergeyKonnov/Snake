package server.mvp.Presenter;

import server.mvp.View.IViewServer;
import server.mvp.Model.IModelServer;


class Presenter implements IPresenter{
    IViewServer v;
    IModelServer observed_model;
    int id;

    public Presenter(IModelServer _m, IViewServer _v, int _id)
    {
        v = _v;
        observed_model = _m;
        id = _id;

        start();
        observed_model.addPresenter(id,this);
    }

    void start()
    {
        final Presenter p = this;
        new Thread(){
            @Override
            public void run() {
                int code = -2;

                while(code != -1)
                {
                    code = v.getOp();
                    if(code == 1)
                    {
                        observed_model.setCell(id, v.getCell());
                    }
                    if(code == 2)
                    {
                        v.setOp(1);
                        v.setCell(observed_model.getBuffer(id));
                    }
                    if(code == -1)
                    {
                        observed_model.removePresenter(p);
                    }
                }
            }
        }.start();

    }

    @Override
    public void update() {
        v.setOp(1);
        v.setCell(observed_model.getBuffer(id));
    }

}
