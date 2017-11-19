package com.kchmielewski.sda.ai.classification.letter.trait;

import com.kchmielewski.sda.ai.classification.letter.Letter;

public class LeftQuarterToAll implements Trait {
    @Override
    public double calculate(Letter value) {
        boolean[][] image = value.getImage();
        int center = image[0].length / 4;
        int matchingPixels = 0;
        int otherPixels = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j]) {
                    if (j < center) {
                        matchingPixels++;
                    } else {
                        otherPixels++;
                    }
                }
            }
        }
        if (matchingPixels == 0) {
            return 0;
        }

        return (double) matchingPixels / (matchingPixels + otherPixels);
    }
}
