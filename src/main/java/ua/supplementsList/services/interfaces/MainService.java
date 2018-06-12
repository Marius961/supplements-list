package ua.supplementsList.services.interfaces;

import ua.supplementsList.models.Classification;
import ua.supplementsList.models.Supplement;

import java.util.List;

public interface MainService {

    List<Supplement> getSupplements();

    List<Supplement> searchSupplements(String request);

    void removeSupplement(int id);

    void addSupplement(Supplement supplement);

    void updateSupplement(Supplement supplement);
}
