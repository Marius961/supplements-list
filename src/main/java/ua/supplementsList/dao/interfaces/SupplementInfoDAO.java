package ua.supplementsList.dao.interfaces;

import ua.supplementsList.models.SupplementInfo;

public interface SupplementInfoDAO {

    int insertInfo(SupplementInfo info);

    SupplementInfo getInfo(int id);

    void updateInfo(SupplementInfo info);

    void removeInfo(int id);
}
