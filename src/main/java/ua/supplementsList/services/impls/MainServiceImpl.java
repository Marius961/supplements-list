package ua.supplementsList.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.supplementsList.dao.interfaces.ClassificationDAO;
import ua.supplementsList.dao.interfaces.SupplementDAO;
import ua.supplementsList.dao.interfaces.SupplementInfoDAO;
import ua.supplementsList.models.Classification;
import ua.supplementsList.models.Supplement;
import ua.supplementsList.models.SupplementInfo;
import ua.supplementsList.services.interfaces.MainService;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    private ClassificationDAO classificationDAO;
    private SupplementInfoDAO supplementInfoDAO;
    private SupplementDAO supplementDAO;

    @Autowired
    private void setDAO(ClassificationDAO classificationDAO, SupplementDAO supplementDAO, SupplementInfoDAO supplementInfoDAO) {
        this.supplementDAO = supplementDAO;
        this.supplementInfoDAO = supplementInfoDAO;
        this.classificationDAO = classificationDAO;
    }

    @Override
    public List<Supplement> getSupplements() {
        List<Supplement> supplements = supplementDAO.getSupplements();
        for (Supplement supplement: supplements) {
            SupplementInfo info = supplementInfoDAO.getInfo(supplement.getInfoId());
            Classification classification = classificationDAO.getClassification(info.getClassificationId());
            info.setClassification(classification);
            supplement.setInfo(info);
        }
        return supplements;
    }

    public Supplement getSupplement(int id) {
        Supplement supplement = supplementDAO.getSupplement(id);
        SupplementInfo info = supplementInfoDAO.getInfo(supplement.getInfoId());
        Classification classification = classificationDAO.getClassification(info.getClassificationId());
        info.setClassification(classification);
        supplement.setInfo(info);
        return supplement;
    }
    @Override
    public void removeSupplement(int id) {
        Supplement supplement = getSupplement(id);
        supplementDAO.removeSupplement(id);
        supplementInfoDAO.removeInfo(supplement.getInfoId());
        int classificationId = supplement.getInfo().getClassificationId();
        int statCount = classificationDAO.getClassificationCount(classificationId);
        if (statCount == 0) {
            classificationDAO.removeClassification(classificationId);
        }
    }

    @Override
    public void addSupplement(Supplement supplement) {
        String name = supplement.getInfo().getClassification().getName();
        Classification classification = classificationDAO.getClassification(name);
        int id = 0;
        if (classification == null) {
            id = classificationDAO.insertClassification(supplement.getInfo().getClassification());

        } else {
            id = classification.getId();
        }
        SupplementInfo info = supplement.getInfo();
        info.setClassificationId(id);
        int infoId = supplementInfoDAO.insertInfo(info);
        supplement.setInfoId(infoId);
        supplementDAO.insertSupplement(supplement);
    }

    @Override
    public void updateSupplement(Supplement supplement) {
        supplementDAO.updateSupplement(supplement);
        supplementInfoDAO.updateInfo(supplement.getInfo());
        classificationDAO.updateClassification(supplement.getInfo().getClassification());
    }

}
