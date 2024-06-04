import os

def read_quiz(filename):
    questions = []
    answers = []
    reading_questions = True

    with open(filename, 'r') as file:
        lines = file.readlines()
    
    for line in lines:
        line = line.strip()
        if line == "ANSWER KEY":
            reading_questions = False
            continue
        
        if reading_questions:
            if line and line[0].isdigit():
                questions.append(line)
        else:
            if line and line[0].isdigit():
                answers.append(line)
    
    return questions, answers

def ask_questions(questions):
    user_answers = []
    print("Please answer the following questions:\n")
    for question in questions:
        print(question)
        answer = input("Your answer: ")
        user_answers.append(answer)
    return user_answers

def evaluate_answers(user_answers, correct_answers):
    score = 0
    correct_count = 0
    total_questions = len(correct_answers)
    
    for user_answer, correct_answer in zip(user_answers, correct_answers):
        correct_answer_text = correct_answer.split(". ")[1].strip()
        if user_answer.strip().lower() == correct_answer_text.lower():
            correct_count += 1
    
    score = (correct_count / total_questions) * 100
    return score, correct_count, total_questions

def provide_feedback(score, correct_count, total_questions, correct_answers):
    print(f"\nYou answered {correct_count} out of {total_questions} questions correctly.")
    print(f"Your score: {score:.2f}%")
    print("\nCorrect Answers:")
    for answer in correct_answers:
        print(answer)

def take_quiz(quiz_filename):
    if not os.path.exists(quiz_filename):
        print(f"Quiz file '{quiz_filename}' not found.")
        return

    questions, correct_answers = read_quiz(quiz_filename)
    user_answers = ask_questions(questions)
    score, correct_count, total_questions = evaluate_answers(user_answers, correct_answers)
    provide_feedback(score, correct_count, total_questions, correct_answers)

if __name__ == "__main__":
    quiz_filename = "quizzes/quiz1.txt"  # Adjust the filename as needed
    take_quiz(quiz_filename)
