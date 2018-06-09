package ua.supplementsList.dao.interfaces;

import ua.supplementsList.models.Supplement;

public interface SupplementDAO {

    void insertSupplement(Supplement supplement);

    Supplement getSupplement(int id);

    void updateSupplement(Supplement supplement);

    void removeSupplement(int id);
}
