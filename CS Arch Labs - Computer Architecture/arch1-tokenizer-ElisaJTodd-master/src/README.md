Project 1: Tokenizer
====================
# Instructions:

You should document your tokenizer in this file.

This file is written in the refreshingly simple `markdown` text
formatting language.

To learn about text formatting using markdown, we encourage you to examine 
 - [../README.md](../README.md)
 - the online [Markdown Guide](https://www.markdownguide.org/).

# MILESTONE 1

- copy a string inputted by the user
- one method

# MILESTONE 2

## [2/14/2020]
- space_char: returns true if " ", or "\t"
- non_space_char: returns false if "\t", or " "
## [2/15/2020]
- word_start: finds the first space_char and returns the next char pointer
- word_end: finds the first space_char and returns that
- count_words: counts the non_space_chars that are followed by a space or "\0"

# MILESTONE 3

## [2/16/2020]
- copy_str: allocates a space in memory to copy a string and returns
	that pointer

# MILESTONE 4 and 5

## [2/21/2020]

- print_tokens: prints tokens in array
- tokenizer: stores tokens in array
- free_tokens: frees tokens, then frees token pointer

# FINAL

## [2/24/2020]

- history
- init_history, add_history, get_history, free_history
- interface
- Added a method to count the length of a string for convenience in tokenizer.
- Added comments
- Made Makefile
