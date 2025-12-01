import json

import requests
from bs4 import BeautifulSoup



def scrape_hero_details(hero_url):

    resp = requests.get(hero_url)
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")

    hero = {}

    hero['name'] = soup.find("h1", id="firstHeading").text

    body_content_div = soup.find('div', class_='mw-body-content')

    hero_content_left_side = body_content_div.find('div').find_all('span', recursive=False)[1].find('div')
    hero_content_left_side_contents = hero_content_left_side.find_all(['div', 'span'], recursive=False)

    hero['fullName'] = hero_content_left_side_contents[1].text
    hero['gender'] = hero_content_left_side_contents[5].text
    hero['race'] = hero_content_left_side_contents[6].text

    hero['attack'] = hero_content_left_side_contents[8].text
    hero['defence'] = hero_content_left_side_contents[10].text
    hero['spellPower'] = hero_content_left_side_contents[12].text
    hero['knowledge'] = hero_content_left_side_contents[14].text

    hero['description'] = hero_content_left_side_contents[15].text

    hero['trait'] = hero_content_left_side_contents[17].text

    hero['skills'] = [{'maturity': hero_content_left_side_contents[19].text
                          , 'type': hero_content_left_side_contents[20].text},
                      {'maturity': hero_content_left_side_contents[22].text
                          , 'type': hero_content_left_side_contents[23].text}
                      ]
    hero['startSummons'] = [
        hero_content_left_side_contents[25].text.replace('–', '-').replace(' ', '/'),
        hero_content_left_side_contents[27].text.replace('–', '-').replace(' ', '/'),
        hero_content_left_side_contents[29].text.replace('–', '-').replace(' ', '/')
    ]

    hero['startSpell'] = hero_content_left_side_contents[-2].text
    hero['movementPower'] = hero_content_left_side_contents[-1].text

    print(hero)

    return hero


def main():
    with open('../../resources/data/heroes.json', 'r') as file:
        heroes_short = json.load(file)

    for hero_short in heroes_short:

        hero = scrape_hero_details(hero_short['selfLink'])

        filename = f'../../resources/data/heroes_details/{hero['name']}.json'

        with open(filename, 'w') as f:
            json.dump(hero, f, indent=4)  # indent=4 for pretty-printing (optional)

        print(f"Data successfully saved to {filename}")


if __name__ == "__main__":
    main()
