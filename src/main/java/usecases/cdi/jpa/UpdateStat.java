package usecases.cdi.jpa;

import entities.Creature;
import entities.Stat;
import persistence.CreaturesDAO;
import persistence.StatsDAO;

import lombok.Getter;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model //Req scoped and Named
public class UpdateStat implements Serializable {
    @Inject
    private CreaturesDAO creaturesDAO;
    @Inject
    private StatsDAO statsDAO;

    @Getter
    private Stat statToUpdate = new Stat();
    @Getter
    private Stat conflictingStat = new Stat();

    @Getter
    private List<Stat> allStats;
    @Getter
    private List<Creature> allCreatures;

    @PostConstruct
    public void init() {
        loadAll();
    }

    public void loadAll() {
        this.allCreatures = creaturesDAO.loadAll();
        this.allStats = statsDAO.loadAll();
    }

    public void prepareForEditing(Stat stat) {
        statToUpdate = stat;
        conflictingStat = null;
    }

    @Transactional
    public void updateSelectedStat() {
        try {
            statsDAO.updateAndFlush(statToUpdate);
            loadAll();
        } catch (OptimisticLockException ole) {
            conflictingStat = statsDAO.loadById(statToUpdate.getId());
            // Pranešam PrimeFaces dialogui, kad užsidaryti dar negalima:
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    @Transactional
    public void overwriteStat() {
        statToUpdate.setOptLockVersion(conflictingStat.getOptLockVersion());
        updateSelectedStat();
    }
}

