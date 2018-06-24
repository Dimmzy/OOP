package levels;

import collidables.Block;

/**
 * BlockCreator interface.
 */
public interface BlockCreator {

    /**
     * Creates a block in the specific location.
     * @param xpos x position of the new block.
     * @param ypos y position of the new block.
     * @return returns the new block.
     */
    Block create(int xpos, int ypos);
}