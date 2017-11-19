package com.kchmielewski.sda.ai.classification.decisiontree;

public interface DecisionTree<Input, Output> {
    DecisionTree<Input, Output> yes();

    DecisionTree<Input, Output> no();

    void yes(DecisionTree<Input, Output> answer);

    void no(DecisionTree<Input, Output> answer);

    Output ask(Input input);
}
