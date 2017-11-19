package com.kchmielewski.sda.ai.classification.decisiontree.example;

import com.kchmielewski.sda.ai.classification.decisiontree.AnswerNode;
import com.kchmielewski.sda.ai.classification.decisiontree.QuestionNode;

import java.util.function.Predicate;

public class ClassifyingNumbers {
    public static void main(String[] args) {
        Predicate<Integer> even = (i) -> i % 2 == 0;
        Predicate<Integer> positive = (i) -> i > 0;

        QuestionNode<Integer, String> evenInteger = new QuestionNode<>(even);
        QuestionNode<Integer, String> positiveInteger = new QuestionNode<>(positive);

        positiveInteger.yes(new AnswerNode<>("Number is positive!"));
        positiveInteger.no(new AnswerNode<>("Number is NOT positive!"));
        evenInteger.yes(positiveInteger);
        evenInteger.no(new AnswerNode<>("Number is not even!"));

        System.out.println(evenInteger.ask(-3));
        System.out.println(evenInteger.ask(1));
        System.out.println(evenInteger.ask(2));
        System.out.println(evenInteger.ask(-2));
    }
}
