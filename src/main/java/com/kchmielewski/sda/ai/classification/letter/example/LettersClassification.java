package com.kchmielewski.sda.ai.classification.letter.example;

import com.kchmielewski.sda.ai.classification.decisiontree.AnswerNode;
import com.kchmielewski.sda.ai.classification.decisiontree.DecisionTree;
import com.kchmielewski.sda.ai.classification.decisiontree.QuestionNode;
import com.kchmielewski.sda.ai.classification.letter.Letter;
import com.kchmielewski.sda.ai.classification.letter.trait.HigherHalfToAll;
import com.kchmielewski.sda.ai.classification.letter.trait.LeftQuarterToAll;
import com.kchmielewski.sda.ai.classification.letter.trait.MiddleThirdToAll;
import com.kchmielewski.sda.ai.classification.letter.trait.Trait;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LettersClassification {
    public static void main(String[] args) {
        List<Letter> letters = findLetters();

        Trait higherHalfToAllTrait = new HigherHalfToAll();
        Trait middleThirdToAllTrait = new MiddleThirdToAll();
        Trait leftQuarterToAll = new LeftQuarterToAll();

        Predicate<Letter> isLetterT = (l) -> higherHalfToAllTrait.calculate(l) > 0.6;
        Predicate<Letter> isLetterAInsteadOfC = (l) -> middleThirdToAllTrait.calculate(l) > 0.15;
        Predicate<Letter> isLetterK = (l) -> leftQuarterToAll.calculate(l) > 0.5;

        DecisionTree<Letter, String> letterT = new QuestionNode<>(isLetterT);
        DecisionTree<Letter, String> letterK = new QuestionNode<>(isLetterK);
        DecisionTree<Letter, String> letterAInsteadOfC = new QuestionNode<>(isLetterAInsteadOfC);

        letterT.yes(new AnswerNode<>("Given letter is 'T'"));
        letterT.no(letterK);

        letterK.yes(new AnswerNode<>("Given letter is 'K'"));
        letterK.no(letterAInsteadOfC);

        letterAInsteadOfC.yes(new AnswerNode<>("Given letter is 'A'"));
        letterAInsteadOfC.no(new AnswerNode<>("Given letter is 'C'"));

        letters.forEach(l -> System.out.println(l.getName() + ": " + letterT.ask(l)));
    }

    private static List<Letter> findLetters() {
        String directoryName = "src/main/resources/classification/";
        String[] files = new File(directoryName).list();
        if (files == null) {
            throw new RuntimeException(String.format("Directory %s is empty or IO error occurred.", directoryName));
        }
        List<String> fileNames = Arrays.asList(files);

        return fileNames.stream().map(f -> new Letter(f, directoryName + f)).collect(Collectors.toList());
    }
}
