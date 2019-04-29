package decorators;

import entities.Stat;
import persistence.CreaturesDAO;

import lombok.*;
import usecases.cdi.jpa.Creatures;
import usecases.cdi.jpa.ICreateStat;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@Decorator
public class StatMappingDecorator implements ICreateStat, Serializable {
    @Inject
    @Delegate
    @Any
    Creatures creaturesUsecase;

    @Inject
    CreaturesDAO creaturesDAO;

    @Transactional
    public String createStat() {
        final Stat statToCreate = creaturesUsecase.getStatToCreate();
        val previousCreatureStats = creaturesDAO.loadCreatureStats(statToCreate.getCreature().getId());
        for (Stat creatStat : previousCreatureStats) {
            if (!creatStat.getSpecialAttribute().equals(statToCreate.getSpecialAttribute())) {
                this.creaturesUsecase.createStat();
                return "Warning: Creature has Stats with a different specialAttribute";
            }
        }
        return this.creaturesUsecase.createStat();
    }
}
