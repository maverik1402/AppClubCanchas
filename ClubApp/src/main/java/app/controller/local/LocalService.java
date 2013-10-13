package app.controller.local;

import app.dao.DAOExcepcion;
import app.dao.LocalDAO;
import app.model.Local;
import java.util.Collection;
import java.util.List;

public class LocalService {

    private LocalDAO localdao = null;

    public LocalService() {
        localdao = new LocalDAO();
    }

    public Collection<Local> list() throws DAOExcepcion {
        return localdao.listar();
    }

    public Local get(Local local) throws DAOExcepcion {
        return localdao.obtener(local.getId());
    }

    public Local save(Local local) throws DAOExcepcion {
        return localdao.actualizar(local);
    }

    public void delete(Local local) throws DAOExcepcion {
        localdao.eliminar(local);
    }
}
