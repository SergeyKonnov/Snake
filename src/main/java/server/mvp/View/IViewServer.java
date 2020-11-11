package server.mvp.View;


import resources.Cell;

public interface IViewServer {
    int getOp();
    void setOpAndCell(int p_id, int op, final Cell point);
    Cell getCell();
}
