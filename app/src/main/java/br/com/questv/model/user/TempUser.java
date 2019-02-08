package br.com.questv.model.user;

import br.com.questv.model.question.answer.AnswerModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TempUser {

  private static List<AnswerModel> answered = new ArrayList<>();

  public static void attachAnsweredQuestion(final AnswerModel answerModel) {
    answered.add(answerModel);
    System.out.println(answered);
  }

  @NotNull
  public static Boolean hasAnswered(@NotNull AnswerModel answerModel) {
    boolean result = false;
    for (final AnswerModel answerModel1 : answered) {
      if (answerModel1.equals(answerModel)) {
        result = true;
        break;
      }
    }
    return result;
  }

  @NotNull
  public static Boolean hasAnswered(@NotNull List<AnswerModel> answerModelList) {
    boolean result = false;
    for (final AnswerModel answerModel : answerModelList) {
      if (hasAnswered(answerModel)) {
        result = true;
        break;
      }
    }
    return result;
  }
}
