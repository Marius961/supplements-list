package ua.supplementsList.dao.interfaces;

import ua.supplementsList.models.Classification;

public interface ClassificationDAO {

    int insertClassification(Classification classification);

    Classification getClassification(int id);

    Classification getClassification(String name);

    void updateClassification(Classification classification);

    void removeClassification(int id);

    int getClassificationCount(int id);
}
