package entities;

import java.io.Serializable;

public interface ISkill extends Serializable {
    int getId();

    void setId(int id);

    void addHero(Hero hero);

    void removeHero(Hero hero);

    boolean equals(Object o);

    int hashCode();

    void setName(String name);

    void setLevelRequirement(byte levelRequirement);

    void setDescription(String description);

    void setPictureUrl(String pictureUrl);

    void setHeroes(java.util.Set<Hero> heroes);

    String toString();

    String getName();

    byte getLevelRequirement();

    String getDescription();

    String getPictureUrl();

    java.util.Set<Hero> getHeroes();
}
