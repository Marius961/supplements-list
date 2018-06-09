package ua.supplementsList.dao.interfaces;

import ua.supplementsList.models.Classification;

public interface ClassificationDAO {

    void insertClassification(Classification classification);

    Classification getClassification(int id);

    void updateClassification(Classification classification);

    void removeClassification(int id);
}
