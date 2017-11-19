package com.kchmielewski.sda.ai.classification.letter.trait;

import com.kchmielewski.sda.ai.classification.letter.Letter;

public interface Trait {
    double calculate(Letter value);
}
