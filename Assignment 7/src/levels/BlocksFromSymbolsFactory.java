package levels;

import collidables.Block;
import geometry.Point;
import utilities.ColorsParser;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Creates blocks from their symbols.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator>  blockCreators;


    /**
     * Initializes two maps that hold the spacers and the blocks.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blockCreators = new TreeMap<String, BlockCreator>();
    }

    /**
     * @param s the String we check.
     * @return returns if the string is a symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * @param s the String we check.
     * @return returns if the string is a block.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Returns a created block on the specified location with s as it's symbol.
     * @param s the symbol of the block.
     * @param x x location.
     * @param y y location
     * @return returns the created block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Returns the width of the spacers.
     * @param s the spacer.
     * @return the width of the spacer.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Adds a block to the blocks map.
     * @param blockDef the definition-extracted string we'll use to extract the block.
     * @throws Exception throws exception if the block values are invalid (negative hp/width/height).
     */
    public void addBlock(Map<String, String> blockDef) throws Exception {
        int height = Integer.parseInt(blockDef.get("height"));
        int width = Integer.parseInt(blockDef.get("width"));
        int hp = Integer.parseInt(blockDef.get("hit_points"));
        if (height <= 0 || width <= 0 || hp <= 0) {
            throw new Exception("Invalid block values");
        }
        Color stroke = null;
        Color defColor = null;
        Image defImage = null;
        Map<Integer, Color> colorFill = new TreeMap<Integer, Color>();
        Map<Integer, Image> imageFill = new TreeMap<Integer, Image>();
        for (int i = 1; i <= hp; i++) {
            if (blockDef.containsKey("fill-" + String.valueOf(i))) {
                String currentFill = blockDef.get("fill-" + String.valueOf(i));
                if (currentFill.startsWith("image")) {
                    try {
                        Image image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(currentFill
                                .substring(6, currentFill.length() - 1)));
                        imageFill.put(i, image);
                    } catch (IOException e) {
                        System.out.println("Failed reading image file");
                        e.printStackTrace();
                        System.exit(1);
                    }
                } else {
                    Color color = new ColorsParser().colorFromString(currentFill);
                    colorFill.put(i, color);
                }
            }
        }
        if (blockDef.containsKey("fill")) {
            String background = blockDef.get("fill");
            if (background != null && background.contains("color")) {
                defColor = new ColorsParser().colorFromString(blockDef.get("fill"));
            } else {
                defImage = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(blockDef.get("fill")
                        .substring(6, blockDef.get("fill").length() - 1)));
            }
        }
        if (blockDef.containsKey("stroke")) {
            stroke = new ColorsParser().colorFromString(blockDef.get("stroke"));
        }
        final Color defColorFinal = defColor;
        final Image defImageFinal = defImage;
        final Color defStrokeFinal = stroke;
        BlockCreator newBlock = new BlockCreator() {
            @Override
            public Block create(int xpos, int ypos) {
                Block newBlock = new Block(new Point(xpos, ypos), width, height, hp);
                newBlock.setDefFillColor(defColorFinal);
                newBlock.setDefFillImage(defImageFinal);
                newBlock.setBorderColor(defStrokeFinal);
                newBlock.setBlockColor(colorFill);
                newBlock.setBlockImage(imageFill);
                return newBlock;
            }
        };
        this.blockCreators.put(blockDef.get("symbol"), newBlock);
    }

    /**
     * Adds the spacers to the spacers map.
     * @param spacer the spacer to add to the map.
     */
    public void addSpacer(Map<String, Integer> spacer) {
        spacerWidths.putAll(spacer);
    }
}
