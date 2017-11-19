package com.kchmielewski.sda.ai.classification.letter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Letter {
    private final String name;
    private final boolean[][] image;

    public Letter(String name, String path) {
        this.name = name;
        this.image = determineImage(path);
    }

    private boolean[][] determineImage(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        int black = Color.BLACK.getRGB();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (image.getRGB(i, j) == black) {
                    if (i < minX) {
                        minX = i;
                    }
                    if (j < minY) {
                        minY = j;
                    }
                    if (i > maxX) {
                        maxX = i;
                    }
                    if (j > maxY) {
                        maxY = j;
                    }
                }
            }
        }

        boolean result[][] = new boolean[maxY - minY][maxX - minX];

        for (int i = minY; i < maxY; i++) {
            for (int j = minX; j < maxX; j++) {
                result[i - minY][j - minX] = image.getRGB(j, i) == black;
            }
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public boolean[][] getImage() {
        return image;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + "\n");
        for (boolean[] column : image) {
            for (int j = 0; j < image[0].length; j++) {
                if (column[j]) {
                    builder.append('*');
                } else {
                    builder.append(' ');
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
