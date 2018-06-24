package levels;

import java.util.List;

/**
 * A class that holds a difficulties set name and it's level information (used for mapping).
 */
public class SetInformation {

    private String name;
    private List<LevelInformation> set;

    /**
     * Constructs the level set information.
     * @param name the name of the difficulty.
     * @param levels the set of levels.
     */
    public SetInformation(String name, List<LevelInformation> levels) {
        this.name = name;
        this.set = levels;
    }

    /**
     * @return returns a list of the sets levels.
     */
    public List<LevelInformation> getLevels() {
        return set;
    }

    /**
     * @return returns the name of the set.
     */
    public String getName() {
        return this.name;
    }

}
