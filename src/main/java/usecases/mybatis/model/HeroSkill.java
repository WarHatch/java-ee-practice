package usecases.mybatis.model;

public class HeroSkill {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.HERO_SKILL.HEROES_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    private Integer heroesId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.HERO_SKILL.SKILLS_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    private Integer skillsId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.HERO_SKILL.HEROES_ID
     *
     * @return the value of PUBLIC.HERO_SKILL.HEROES_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    public Integer getHeroesId() {
        return heroesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.HERO_SKILL.HEROES_ID
     *
     * @param heroesId the value for PUBLIC.HERO_SKILL.HEROES_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    public void setHeroesId(Integer heroesId) {
        this.heroesId = heroesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.HERO_SKILL.SKILLS_ID
     *
     * @return the value of PUBLIC.HERO_SKILL.SKILLS_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    public Integer getSkillsId() {
        return skillsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.HERO_SKILL.SKILLS_ID
     *
     * @param skillsId the value for PUBLIC.HERO_SKILL.SKILLS_ID
     *
     * @mbg.generated Fri Mar 08 22:23:27 EET 2019
     */
    public void setSkillsId(Integer skillsId) {
        this.skillsId = skillsId;
    }
}