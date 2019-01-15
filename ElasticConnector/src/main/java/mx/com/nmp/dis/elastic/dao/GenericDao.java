package mx.com.nmp.dis.elastic.dao;

public interface GenericDao<G> {

    G insert(G g);

    G getById(String id);

    G updateById(String id, G g);

    void deleteBookById(String id);

}