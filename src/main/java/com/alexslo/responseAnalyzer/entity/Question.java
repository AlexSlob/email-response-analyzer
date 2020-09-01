package com.alexslo.responseAnalyzer.entity;

import java.util.Objects;

public class Question {

    private final Integer questionTypeId;
    private final Integer categoryId;
    private final Integer subcategoryId;
    private static final String DOT_PATTERN = "\\.";
    private static final String ASTERISK = "*";

    public Question(Integer categoryId) {
        this(categoryId, null, null);
    }

    public Question(Integer questionTypeId, Integer categoryId) {
        this(questionTypeId, categoryId, null);
    }

    public Question(Integer questionTypeId, Integer categoryId, Integer subcategoryId) {
        this.questionTypeId = questionTypeId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
    }

    public static Question fromString(String value) {
        Objects.requireNonNull(value);

        if (value.equals(ASTERISK))
            return new Question(null);

        String[] parts = value.split(DOT_PATTERN);

        if (parts.length > 3) {
            throw new IllegalArgumentException("Too much arguments");
        }
        int questionIndex = 0;
        int categoryIndex = 1;
        int subcategoryIndex = 2;

        if (parts.length == 3) {
            return new Question(Integer.parseInt(parts[questionIndex]),
                    Integer.parseInt(parts[categoryIndex]),
                    Integer.parseInt(parts[subcategoryIndex]));
        } else {
            return parts.length == 2 ?
                    new Question(Integer.parseInt(parts[questionIndex]), Integer.parseInt(parts[categoryIndex])) :
                    new Question(Integer.parseInt(parts[questionIndex]));
        }
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public boolean matches(Question o) {
        return matchesQuestionTypeId(o.getQuestionTypeId()) &&
                matchesCategoryId(o.getCategoryId()) &&
                matchesSubcategoryId(o.getSubcategoryId());
    }

    private boolean matchesQuestionTypeId(Integer questionTypeId) {
        return this.questionTypeId == null || questionTypeId == null || this.questionTypeId == questionTypeId;

    }

    private boolean matchesCategoryId(Integer categoryId) {
        return this.categoryId == null || categoryId == null || this.categoryId == categoryId;

    }

    private boolean matchesSubcategoryId(Integer subcategoryId) {
        return this.subcategoryId == null || subcategoryId == null || this.subcategoryId == subcategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionTypeId, question.questionTypeId) &&
                Objects.equals(categoryId, question.categoryId) &&
                Objects.equals(subcategoryId, question.subcategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionTypeId, categoryId, subcategoryId);
    }
}
