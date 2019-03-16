package usecases.cdi.myBatis;

import usecases.mybatis.dao.*;
import usecases.mybatis.model.*;

import lombok.Getter;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class CreatureWithStat implements Serializable {

    @Inject
    private CreatureMapper creatureMapper;
    @Inject
    private StatMapper statMapper;

    @Getter
    private Creature creatureToCreate = new Creature();
    @Getter
    private Stat statToCreate = new Stat();

    @Getter
    private List<entities.Stat> creatureStats;
    @Getter
    private List<Creature> allCreatures;

    @PostConstruct
    public void init(){
        loadCreatures();
    }

    public void loadCreatures() {
        this.allCreatures = creatureMapper.selectAll();
    }

    public List<Creature> getAllCreatures(){
        return allCreatures;
    }

//    public List<entities.Stat> getAllStats(){
//        return allStats;
//    }

    public List<Stat> getCreatureStats(int creatureId){
        return statMapper.selectAllByCreaturePrimaryKey(creatureId);
    }

    @Transactional
    public String createCreature(){
        this.creatureMapper.insert(creatureToCreate);
        return "success";
    }

    @Transactional
    public String createStat(){
        this.statMapper.insert(statToCreate);
        return "success";
    }
}