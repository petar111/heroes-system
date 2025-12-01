import json

import requests
from bs4 import BeautifulSoup
from service.provider.url_provider import BASE_URL, LIST_OF_HEROES_URL
from service.util.clean_utils import clean

from service.util.url_utils import compose_url


def extract_heroes_from_table(table, headers):
    heroes = []
    for row in table.find_all('tr')[1:]:
        hero = {}
        cols = row.find_all('td')

        for index, header in enumerate(headers):
            cell_value = ''

            if not header:
                continue


            if header == 'expansionPack':
                cell_value = clean(cols[index].get_text()).strip()
                if not cell_value:
                    cell_value = cols[index].find('a')['title']

            if not cell_value:
                cell_value = cols[index].get_text()

            hero[header] = clean(cell_value).strip()

        # add links
        hero['selfLink'] = BASE_URL + cols[0].find("a")['href']

        heroes.append(hero)
    return heroes

def scrape_heroes():
    resp = requests.get(compose_url(LIST_OF_HEROES_URL))
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")

    table = soup.find("table")

    headers = ['name','class','expansionPack','','specialty','','skill1','','skill2','','spell']

    return extract_heroes_from_table(table, headers)

def main():
    filename = '../../resources/data/heroes.json'

    heroes = scrape_heroes()

    with open(filename, 'w') as f:
        json.dump(heroes, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")

if __name__ == "__main__":
    main()