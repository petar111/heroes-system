import json

import requests
from bs4 import BeautifulSoup
from service.provider.url_provider import BASE_URL, LIST_OF_SPELLS_URL
from service.util.clean_utils import clean

from service.util.clean_utils import to_camel_case
from service.util.url_utils import compose_url


def extract_spells_from_table(table, headers):
    spells = []
    for row in table.find_all('tr')[1:]:
        spell = {}
        cols = row.find_all('td')

        for index, header in enumerate(headers):
            cell_value = ''

            if header == 'School':
                cell_value = clean(cols[index].get_text()).strip()
                if not cell_value:
                    cell_value = cols[index].find('a')['title']
            if header == 'Creature Caster' or header == 'Artifact Caster':
                cell_value = ",".join(set(map(lambda c: c['title'], cols[index].find_all('a', recursive=True))))

            if not cell_value:
                cell_value = cols[index].get_text()

            spell[to_camel_case(header)] = clean(cell_value).strip()

        # add links
        spell['selfLink'] = BASE_URL + cols[0].find("a")['href']

        if cols[1].find("a"):
            spell['schoolMagicLink'] = BASE_URL + cols[1].find("a")['href']

        spells.append(spell)
    return spells

def scrape_spells():
    resp = requests.get(compose_url(LIST_OF_SPELLS_URL))
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")
        
    # Combat Damage Spells
    table = soup.find("table")
    headers = ['Name','School','Level','Cost','Damage Equation','AOE Size','1 Dmg','1 Dmg/SP','10 Dmg','10 Dmg/SP','25 Dmg','25 Dmg/SP','Notes','Creature Caster']

    combat_damage_spells = extract_spells_from_table(table, headers)

    # Combat Utility Spells
    headers = ['Name', 'School', 'Level', 'Cost', 'Type', 'Effect', 'Creature Caster', 'Artifact Caster']
    table = table.find_next('table')

    combat_utility_spells = extract_spells_from_table(table, headers)

    #Adventure Map spells
    headers = ['Name', 'School', 'Level', 'Cost', 'Effect']
    table = table.find_next('table')

    adventure_map_spells = extract_spells_from_table(table, headers)


    return {'Combat Damage Spells' : combat_damage_spells, 'Combat Utility Spells' : combat_utility_spells, 'Advent Map Spells' : adventure_map_spells}

def main():
    filename = '../../resources/data/spells.json'

    spells = scrape_spells()

    with open(filename, 'w') as f:
        json.dump(spells, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")

if __name__ == "__main__":
    main()