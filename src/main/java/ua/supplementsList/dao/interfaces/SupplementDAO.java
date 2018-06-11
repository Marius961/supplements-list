package ua.supplementsList.dao.interfaces;

import ua.supplementsList.models.Supplement;

import java.util.List;

public interface SupplementDAO {

    List<Supplement> getSupplements();

    void insertSupplement(Supplement supplement);

    Supplement getSupplement(int id);

    void updateSupplement(Supplement supplement);

    void removeSupplement(int id);
}
