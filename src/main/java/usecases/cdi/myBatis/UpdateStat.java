package usecases.cdi.myBatis;

import lombok.Getter;
import mybatis.dao.CreatureMapper;
import mybatis.dao.StatMapper;
import mybatis.model.Creature;
import mybatis.model.Stat;
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
    private CreatureMapper creatureMapper;
    @Inject
    private StatMapper statMapper;

    @Getter
    private Stat statToUpdate = new Stat();
    @Getter
    private Stat conflictingStat = new Stat();

    @Getter
    private List<Stat> allStats;
    @Getter
    private List<Creature> allCreatures;

    @PostConstruct
    public void init(){
        loadAll();
    }

    public void loadAll() {
        this.allCreatures = creatureMapper.selectAll();
        this.allStats = statMapper.selectAll();
    }

    public void prepareForEditing(Stat stat) {
        statToUpdate = stat;
        conflictingStat = null;
    }

    @Transactional
    public void updateSelectedStat() {
        try {
            statMapper.updateByPrimaryKey(statToUpdate);
            loadAll();
        } catch (OptimisticLockException ole) {
            conflictingStat = statMapper.selectByPrimaryKey(statToUpdate.getId());
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
