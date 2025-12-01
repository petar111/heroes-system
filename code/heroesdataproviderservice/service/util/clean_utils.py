import re

def clean(text):
    return re.sub(r'[^a-zA-Z0-9 ,\-+/\\%–()]', '', text)

def to_camel_case(raw):
    raw_word = (raw.replace('-', ' ').replace('_', ' ').replace('.', ' ').replace(',', ' ')
                .replace('?', ' ').replace('!', ' ').replace(';', ' ').replace(':', ' ')
                .replace(',', ' ').replace('.', ' ').replace('/' ,' '))
    words = raw_word.split(' ')
    words[0] = words[0].lower()
    for word in words[1:]:
        word.capitalize()
    return "".join(words)