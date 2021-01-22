import sys
import random

# Function that defines the game
def play():
    words = ['python', 'java', 'kotlin', 'javascript']
    word = random.choice(words)

    hidden_word = '-' * len(word)
    tries = 0
    used_letters = []

    # While users has tries continue the loop
    while tries < 8:

        print(hidden_word)
        guess = input("Input a letter:")

        # Different Logic outcomes
        if guess in used_letters and not len(guess) > 1:
            print("You already typed this letter")

        elif len(guess) > 1:
            print("You should input a single letter")

        elif not guess.isalpha() or not guess.islower():
            print("It is not an ASCII lowercase letter")

        elif guess not in word:
            print("No such letter in the word")
            tries += 1

        else:
            hidden_word_lst = list(hidden_word)
            word_lst = list(word)
            for index in range(len(word)):
                if word[index] == guess:
                    hidden_word_lst[index] = guess
            hidden_word = ''.join(hidden_word_lst)

        used_letters.append(guess)

        if word == hidden_word:
            print(f"You guessed the word {word}!")
            print("You survived!")
            break

        if tries > 7 and word != hidden_word:
            print('You are hanged!')
            break
        print()

# Menu logic flow
while True:
    print('H A N G M A N')
    play_now = input('Type "play" to play the game, "exit" to quit: ')
    print()
    if play_now == 'play':
        play()
    elif play_now == 'exit':
        sys.exit()
